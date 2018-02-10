${context.package}

<#assign classStatement>
@${context.importClass("javax.persistence.Entity")}
@${context.importClass("javax.persistence.Table")}(name="${context.model.name}")
public class ${context.className}${context.extendStatement}${context.implementStatement} {
<#include "EntityDefaultAttribute.ftl" />
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

