<ui:composition xmlns="http://www.w3.org/1999/xhtml"
                xmlns:h="http://java.sun.com/jsf/html"
                xmlns:f="http://java.sun.com/jsf/core"
                xmlns:ui="http://java.sun.com/jsf/facelets"
                xmlns:syn="http://synergixtech.com/synfaces">
    <h:form>
        <syn:dataTable value="${r"#{"}${context.contextModel.value}${r"}"}"
<#if context.contextModel.styleClass?has_content>
                       var="${context.contextModel.var}"
                       styleClass="${context.contextModel.styleClass}">
<#else>
                       var="${context.contextModel.var}">
</#if>
<#list context.contextModel.columns as column>

            <syn:column labelKey="${column.name}"
                        styleClass="syn-table-column-${column.styleClass}"
                        sortAndFilterBy="${r"#{"}${context.contextModel.var}.${column.name}${r"}"}">
                <syn:outputLabel value="${r"#{"}${context.contextModel.var}.${column.name}${r"}"}"/>
            </syn:column>
</#list>
        </syn:dataTable>
    </h:form>
</ui:composition>