package vn.locdt.jats.synergix.addon.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import vn.locdt.jats.synergix.addon.exception.ColumnExpressionFormatException;
import vn.locdt.jats.synergix.addon.param.ColumnExpression;
import vn.locdt.jats.synergix.addon.param.OrderedColumnExpression;
import vn.locdt.jats.util.common.LogUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class OrderExpressionConverter implements Converter<String, OrderedColumnExpression> {
	@Override
	public OrderedColumnExpression convert(String source) {
		String[] colExpressions = source.split("\\|");
		OrderedColumnExpression exp = new OrderedColumnExpression();
		if (colExpressions.length == 0)
			return exp;

		try {
			for (String colExpStr : colExpressions) {
				ColumnExpression colExp = this.convertToColumnExpression(colExpStr);
				exp.getColumnExpressions().add(colExp);
			}
		}
		catch (ColumnExpressionFormatException e) {
			LogUtils.printErrorLog(e.getMessage());
		}

		return exp;
	}

	private ColumnExpression convertToColumnExpression(String source) throws ColumnExpressionFormatException {
		int index = Integer.valueOf(String.valueOf(source.charAt(0)));
		if (index <= 0) {
			String mes = String.format("Index of column must be greater than 0. (%s)", source);
			throw new ColumnExpressionFormatException(mes);
		}

		String columnAttr = null;
		Matcher m = Pattern.compile("\\s*\\((.*?)\\)\\s*").matcher(source);
		int numOfParam = 1;
		while (m.find()) {
			if (numOfParam > 1) {
				String mes = String.format("Can not declare column's attribute in multiple times. (%s)", source);
				throw new ColumnExpressionFormatException(mes);
			}

			columnAttr = m.group();
			columnAttr = columnAttr.substring(1, columnAttr.length() - 1);
			numOfParam++;
		}

		ColumnExpression colExp = new ColumnExpression(index);
		if (columnAttr != null) {
			String[] attrs = columnAttr.split(",");
			if (attrs.length > 2) {
				String mes = String.format("Number of attribute in column expression must be = 2. (%s)", source);
				throw new ColumnExpressionFormatException(mes);
			}

			colExp.setLabelKey(attrs[0]);
			colExp.setSynColumnStyleClass(attrs[1]);
		}

		return colExp;
	}
}
