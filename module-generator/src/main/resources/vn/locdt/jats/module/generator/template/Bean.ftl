${context.packageStatement}
${context.silentImport(
	context.packageName?replace("uibean", "service") + "." + context.contextModel.contextNameAsClassName + "Service",
	"synergix.th6.business.action.uibean.abstractbean." + context.extendClassName,
	"javax.inject.Inject",
	"javax.inject.Named"
)}
<#assign classStatement>
@Named
public class ${context.className}${context.extendStatement}${context.implementStatement} {

	@Inject
	private ${context.contextModel.contextNameAsClassName}Service ${context.contextModel.contextName}Service;

	@Override
	public String getFormCode() {
		return "${context.contextModel.code}";
	}
}
</#assign>
${context.getImports()}
${classStatement}



