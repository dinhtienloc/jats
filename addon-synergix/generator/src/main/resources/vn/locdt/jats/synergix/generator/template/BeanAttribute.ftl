<#assign fields></#assign>
<#assign getset></#assign>
<#list context.contextModel.columns as column>
<#assign fields>
${fields}${context.generateFieldStatement(column, column.getRelationAsForeignKey())};
</#assign>
<#assign getset>${getset}<#include "EntityMappingGetterAnno.ftl">
${context.generateGetSetStatement(column, relation)}<#if !column?is_last>${"\n\n"}</#if></#assign>
</#list>
<#--<#list context.contextModel.relations as r>-->
<#--<#assign fields>-->
<#--${fields}<#if r?is_first>${"\n"}</#if>${"\t"}private ${context.importClass("java.util.List<"+r.parentColumn.getJavaType()+">")} ${r.parentColumn.getJavaVarName()+"List"};-->
<#--</#assign>-->
<#--<#assign getset>${getset}${"\n"}-->
    <#--<#include "ReferenceMappingGetterAnno.ftl">-->
    <#--public ${context.importClass("java.util.List<"+r.parentColumn.getJavaType()+">")} get${r.parentColumn.getJavaVarName()+"List"}() {-->
        <#--return ${r.parentColumn.getJavaVarName()+"List"};-->
    <#--}-->

    <#--public void set${r.parentColumn.getJavaName()+"List"}(${context.importClass("java.util.List<"+r.parentColumn.getJavaType()+">")} ${r.parentColumn.getJavaVarName()+"List"}) {-->
        <#--this.${r.parentColumn.getJavaVarName()+"List"} = ${r.parentColumn.getJavaVarName()+"List"};-->
    <#--}<#if !r?is_last>${"\n\n"}</#if></#assign>-->
<#--</#list>-->