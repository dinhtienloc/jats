<#if context.contextModel.name == r.childTableName>
    @${context.importClass("javax.persistence.ManyToOne")}(fetch = ${context.importClass("javax.persistence.FetchType")}.LAZY)
    @${context.importClass("javax.persistence.JoinColumn")}(name = "${r.parentColumnName}")<#elseif context.contextModel.name == r.parentTableName>
    ${"\n"}
    @${context.importClass("javax.persistence.OneToMany")}(
        mappedBy = "${r.parentTableName}",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
</#if>

