${context.packageStatement}

<#assign classStatement>
	${context.silentImport("synergix.th6.business.action.service.AbstractService")}
	${context.silentImport("javax.enterprise.context.ConversationScoped")}
@ConversationScoped
public class ${context.className}${context.extendStatement}${context.implementStatement} {

}
</#assign>
${context.getImports()}${classStatement}



