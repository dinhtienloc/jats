<#assign fields></#assign>
<#assign getset></#assign>
<#list context.getMappingAttribute().getColumns() as column>
    <#assign fields +=
    "\tprivate ${context.importClass(column.getJavaType())} ${column.getJavaVarName()};">
    <#assign getset +=
    "\n\tpublic ${context.importClass(column.getJavaType())} get${column.getJavaName()}() {
        return ${column.getJavaVarName()};
    }

    public void set${column.getJavaName()}(${context.importClass(column.getJavaType())} ${column.getJavaVarName()}) {
        this.${column.getJavaVarName()} = ${column.getJavaVarName()};
    }">
    <#if !column?is_last>
        <#assign fields += "\n">
        <#assign getset += "\n">
    </#if>
</#list>