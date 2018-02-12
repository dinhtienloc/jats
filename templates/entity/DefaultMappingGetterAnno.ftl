<#if column.isPrimaryKey()>
    @${context.importClass("javax.persistence.Id")}
    @${context.importClass("javax.persistence.GeneratedValue")}(
        strategy = ${context.importClass("javax.persistence.GenerationType")}.${column.generatedStrategy.strategyType}
    )
</#if>
    @${context.importClass("javax.persistence.Column")}(name = "${column.name}")