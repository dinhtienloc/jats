<ui:composition xmlns="http://www.w3.org/1999/xhtml"
				xmlns:h="http://java.sun.com/jsf/html"
				xmlns:ui="http://java.sun.com/jsf/facelets"
				xmlns:p="http://primefaces.org/ui"
				xmlns:syn="http://synergixtech.com/synfaces">

	<syn:div styleClass="synfaces-dashpane-panel full-height full-width">
		<p:panel styleClass="synfaces-dashpane-panel full-width flex-item-auto-size"
				header="#{messages['th6_maincon_backcharge_summary']}">
			<h:outputText rendered="${r"#{!userService.isDashpaneAccessAllowed('"}${r"')}"}" value="#{messages['screen_access_denied']}"/>

			<h:outputText rendered="#{!userService.isDashpaneAccessAllowed('TH6_MAINCON_BACKCHARGE_SUMMARY')}"
					value="#{messages['screen_access_denied']}"/>

			<h:form rendered="#{userService.isDashpaneAccessAllowed('TH6_MAINCON_BACKCHARGE_SUMMARY')}">
				<syn:dataTable value="#{mainconBackchargeAllocDashpaneBean.pendingVouchers}" var="row"
							   styleClass="synfaces-table-notselectable synfaces-width-full">
					<syn:column labelKey="voucher_no" sortAndFilterBy="#{row.invoice_no}"
								styleClass="synfaces-align-center syn-table-column-code">
						<syn:commandLink value="#{row.invoice_no}"
										 action="#{mainconBackchargeAllocDashpaneBean.switchToScreen(row)}"
										 update=":contentPane, :footerPane"/>
					</syn:column>
					<syn:column labelKey="project_no" sortAndFilterBy="#{row.project_no}"
								styleClass="synfaces-align-center syn-table-column-code">
						<syn:outputLabel value="#{row.project_no}"/>
					</syn:column>

					<syn:column labelKey="age_date" sortAndFilterBy="#{row.age_date}"
								styleClass="synfaces-align-center syn-table-column-date">
						<syn:outputLabel value="#{row.age_date}"/>
					</syn:column>

					<syn:column labelKey="project_name" sortAndFilterBy="#{row.pj_est_ost_hdr.subject}"
								styleClass="synfaces-align-center syn-table-column-code">
						<syn:outputLabel value="#{row.pj_est_ost_hdr.subject}"/>
					</syn:column>

					<syn:column labelKey="customer" sortAndFilterBy="#{row.pj_est_ost_hdr.mt_customer.customerName}"
								styleClass="synfaces-align-center syn-table-column-code">
						<syn:outputLabel value="#{row.pj_est_ost_hdr.mt_customer.customerName}"/>
					</syn:column>

					<syn:column labelKey="unalloc_amt" styleClass="synfaces-align-center syn-table-column-code">
						<syn:outputNumber type="currency-amt" value="#{row.total_before_tax_amt-row.total_alloc_amt}"/>
					</syn:column>
				</syn:dataTable>

			</h:form>
		</p:panel>
	</syn:div>

</ui:composition>
