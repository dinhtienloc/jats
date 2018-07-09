${context.getPackage()}

public class ${context.getClassName()}${context.getExtendStatement()}${context.getImplementStatement()} {
<#foreach column in context.getMappingAttribute().getColumns()>
    private ${column.getJavaType()} ${stringutils.convertSqlNameToVariableName(column.getName())};
</#foreach>
}
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

