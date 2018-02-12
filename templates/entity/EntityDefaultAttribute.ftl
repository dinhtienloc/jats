<#assign fields></#assign>
<#assign getset></#assign>
<#list context.getMappingAttribute().getColumns() as column>
<#assign fields>
${fields}${"\t"}private ${context.importClass(column.getJavaType())} ${column.getJavaVarName()};
</#assign>
<#assign getset>${getset}<#include "EntityGetterAnnotation.ftl">
    public ${context.importClass(column.getJavaType())} get${column.getJavaName()}() {
        return ${column.getJavaVarName()};
    }

    public void set${column.getJavaName()}(${context.importClass(column.getJavaType())} ${column.getJavaVarName()}) {
        this.${column.getJavaVarName()} = ${column.getJavaVarName()};
    }<#if !column?is_last>${"\n\n"}</#if></#assign>
</#list>