package vn.locdt.jats.synergix.addon.command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import vn.locdt.jats.bundle.question.JQuestion;
import vn.locdt.jats.module.generator.FileType;
import vn.locdt.jats.module.generator.TemplateProducer;
import vn.locdt.jats.module.modeling.DatabaseMetadataWrapper;
import vn.locdt.jats.module.modeling.DatabaseReaderFactory;
import vn.locdt.jats.module.modeling.model.Column;
import vn.locdt.jats.module.modeling.model.Table;
import vn.locdt.jats.module.modeling.system.SystemModeling;
import vn.locdt.jats.module.shell.command.QuestionCommand;
import vn.locdt.jats.module.shell.context.ContextKey;
import vn.locdt.jats.module.shell.context.ShellRuntimeContext;
import vn.locdt.jats.synergix.addon.config.SynergixDataType;
import vn.locdt.jats.synergix.addon.db.DatabaseInfo;
import vn.locdt.jats.synergix.generator.DatatableGenerator;
import vn.locdt.jats.synergix.generator.context.DatatableContext;
import vn.locdt.jats.synergix.generator.context.model.DatatableColumnModel;
import vn.locdt.jats.synergix.generator.context.model.DatatableModel;
import vn.locdt.jats.util.common.FileUtils;
import vn.locdt.jats.util.common.LogUtils;
import vn.locdt.jats.util.common.XMLUtils;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@ShellComponent
public class DatatableGenerateCommand extends QuestionCommand {
	@ShellMethod(key = {"datatable"}, value = "Generate xhtml datatable code for a table. Columns are split by comma (,)")
	public String runCommand(
			@ShellOption(value = {"-t", "--table"}) String tableName,
			@ShellOption(value = {"-m", "--module"}) String module,
			@ShellOption(value = {"-o", "--order"}) String order,
			@ShellOption(value = {"-c", "--ctrl"}) boolean isCtrl) {

		DatabaseInfo info = ShellRuntimeContext.getContext(ContextKey.DATABASE_INFO, DatabaseInfo.class);
		String th6Path = ShellRuntimeContext.getContext(ContextKey.TH6_PATH, String.class);
		Assert.notNull(th6Path, LogUtils.createErrorLog("Please set up 'th6Path' config first."));

		final String metaPath = "TH6\\src\\main\\resources\\synergix\\th6\\data\\meta\\" + (isCtrl ? "ctrlschema" : "schema");
		final String tableFileName = StringUtils.capitalize(tableName) + FileType.XML.getExt();
		final String moduleTablePath = FileUtils.path(module.toLowerCase(), tableFileName);
		File xmlFile = new File(FileUtils.path(th6Path, metaPath, moduleTablePath));
		if (!xmlFile.exists()) {
			return LogUtils.createErrorLog(xmlFile.getAbsolutePath() + " file not found");
		}


		Document doc = XMLUtils.parseFile(xmlFile);
		NodeList nl = doc.getElementsByTagName("column");

		try {
			String[] colStrs = order.split(",");
			if (colStrs.length == 0)
				return null;

			int[] intArr = this.convertStrArrToIntArr(colStrs);
			List<DatatableColumnModel> cols = new ArrayList<>();
			int numOfCol = intArr.length;

			for (int i = 0; i < numOfCol; i++) {
				Element colElm = (Element) nl.item(intArr[i]-1);
				String colName = colElm.getAttribute("name").toLowerCase();
				String colDataType = colElm.getAttribute("data-type").toLowerCase();
				String styleClass = SynergixDataType.getStyleClass(colDataType);
				if (styleClass == null) {
					styleClass = colDataType;
				}

				boolean colNotNull = "true".equals(colElm.getAttribute("not-null"));

				DatatableColumnModel columnModel = new DatatableColumnModel(colName, colDataType, styleClass, colNotNull);
				cols.add(columnModel);
			}

			DatatableModel model = this.createDatatableModel(cols);
			DatatableContext ctx = new DatatableContext(model, "generated", tableName);
			DatatableGenerator generator = new DatatableGenerator(ctx);
			generator.setTemplateName("Datatable.ftl");

			TemplateProducer producer = TemplateProducer.createProducer(DatatableGenerator.class, "template");
			generator.generate(producer);
		} catch (NumberFormatException e) {
			LogUtils.printErrorLog("Can't parse column order. Format is wrong", e);
		}

		return null;
	}

	private DatatableModel createDatatableModel(List<DatatableColumnModel> cols) {
		String dtValue = JQuestion.input(this.getLineReader(), "Datatable List Value: ").getValue();
		String dtVar = JQuestion.input(this.getLineReader(), "Datatable Var Value: ").getValue();
		String dtStyleClass = JQuestion.input(this.getLineReader(), "Datatable Style Class: ").getValue();

		DatatableModel dbModel = new DatatableModel(dtValue, dtVar, dtStyleClass);
		dbModel.getColumns().addAll(cols);

		return dbModel;
	}

	private int[] convertStrArrToIntArr(String[] strArr) throws NumberFormatException {
		int strArrLength = strArr.length;
		int[] intArr = new int[strArrLength];
		for (int i = 0; i < strArrLength; i++) {
			intArr[i] = Integer.valueOf(strArr[i]);
		}

		return intArr;
	}
}
