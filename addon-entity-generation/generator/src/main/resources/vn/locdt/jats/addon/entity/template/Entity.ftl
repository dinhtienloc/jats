${context.packageStatement}

<#assign classStatement>
@${context.importClass("javax.persistence.Entity")}
@${context.importClass("javax.persistence.Table")}(name="${context.contextModel.name}")
public class ${context.className}${context.extendStatement}${context.implementStatement} {
<#include "EntityAttribute.ftl" />
${fields}
${getset}
}
</#assign>
${context.getImports()}

${classStatement}

<#--<#assign classbody>-->
<#--<#include "PojoTypeDeclaration.ftl"/> {-->

<#--<#if !entity.isInterface()>-->
<#--<#include "PojoFields.ftl"/>-->

<#--<#include "PojoConstructors.ftl"/>-->
<#---->
<#--<#include "PojoPropertyAccessors.ftl"/>-->

<#--<#include "PojoToString.ftl"/>-->

<#--<#include "PojoEqualsHashcode.ftl"/>-->

<#--<#else>-->
<#--<#include "PojoInterfacePropertyAccessors.ftl"/>-->

<#--</#if>-->
<#--<#include "PojoExtraClassCode.ftl"/>-->

<#--}-->
<#--</#assign>-->

<#--${pojo.generateImports()}-->
<#--${classbody}-->

