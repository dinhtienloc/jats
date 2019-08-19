${context.packageStatement}

<#assign classStatement>
${context.silentImport(context.packageName?replace("uibean", "service"))}
${context.silentImport("javax.inject.Inject")}
${context.silentImport("javax.inject.Named")}
@Named
public class ${context.className}${context.extendStatement}${context.implementStatement} {

	@Inject
	private ${context.contextModel.contextNameAsClassName}Service ${context.contextModel.contextName}Service;
}
</#assign>
${context.getImports()}${classStatement}



