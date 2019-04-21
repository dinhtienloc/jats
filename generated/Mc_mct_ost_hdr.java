package synergix.taskhub5.persistence.temp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import synergix.taskhub5.persistence.domain.AbstractEntity;
import synergix.taskhub5.persistence.domain.AbstractPk;
import synergix.taskhub5.util.SynRound;
import synergix.taskhub5.util.SynString;

@SuppressWarnings("serial")
@Entity
@Table(name="mc_mct_ost_hdr")
public class Mc_mct_ost_hdr extends AbstractEntity {

	// Composite key
	@EmbeddedId
	private Pk pk;
	
	@Override
	public Pk getPk() {return pk;}
	public void setPk(Pk pk) {this.pk = pk;}
	
	// Getters for Pk fields
	public String getMaint_contract_no() {return this.getPk().getMaint_contract_no();}
	public int getRenewal_no() {return this.getPk().getRenewal_no();}

	// Non-key fields:
	@Column(name="revision_no")
	private int revision_no;
	public int getRevision_no() {return revision_no;}
	public void setRevision_no(int revision_no) {this.revision_no = revision_no;}

	@Column(name="master_contract_no")
	private String master_contract_no;
	public String getMaster_contract_no() {return master_contract_no;}
	public void setMaster_contract_no(String master_contract_no) {this.master_contract_no = SynString.trim(master_contract_no);}

	@Column(name="master_renewal_no")
	private int master_renewal_no;
	public int getMaster_renewal_no() {return master_renewal_no;}
	public void setMaster_renewal_no(int master_renewal_no) {this.master_renewal_no = master_renewal_no;}

	@Column(name="contract_type")
	private String contract_type;
	public String getContract_type() {return contract_type;}
	public void setContract_type(String contract_type) {this.contract_type = SynString.trim(contract_type);}

	@Column(name="contract_status")
	private String contract_status;
	public String getContract_status() {return contract_status;}
	public void setContract_status(String contract_status) {this.contract_status = SynString.trim(contract_status);}

	@Column(name="quotation_no")
	private String quotation_no;
	public String getQuotation_no() {return quotation_no;}
	public void setQuotation_no(String quotation_no) {this.quotation_no = SynString.trim(quotation_no);}

	@Column(name="org_contract_no")
	private String org_contract_no;
	public String getOrg_contract_no() {return org_contract_no;}
	public void setOrg_contract_no(String org_contract_no) {this.org_contract_no = SynString.trim(org_contract_no);}

	@Column(name="party_code")
	private String party_code;
	public String getParty_code() {return party_code;}
	public void setParty_code(String party_code) {this.party_code = SynString.trim(party_code);}

	@Column(name="party_address_code")
	private String party_address_code;
	public String getParty_address_code() {return party_address_code;}
	public void setParty_address_code(String party_address_code) {this.party_address_code = SynString.trim(party_address_code);}

	@Column(name="party_contact_code")
	private String party_contact_code;
	public String getParty_contact_code() {return party_contact_code;}
	public void setParty_contact_code(String party_contact_code) {this.party_contact_code = SynString.trim(party_contact_code);}

	@Column(name="delivery_address_code")
	private String delivery_address_code;
	public String getDelivery_address_code() {return delivery_address_code;}
	public void setDelivery_address_code(String delivery_address_code) {this.delivery_address_code = SynString.trim(delivery_address_code);}

	@Column(name="delivery_contact_code")
	private String delivery_contact_code;
	public String getDelivery_contact_code() {return delivery_contact_code;}
	public void setDelivery_contact_code(String delivery_contact_code) {this.delivery_contact_code = SynString.trim(delivery_contact_code);}

	@Column(name="crm_line_item_no")
	private double crm_line_item_no;
	public double getCrm_line_item_no() {return crm_line_item_no;}
	public void setCrm_line_item_no(double crm_line_item_no) {this.crm_line_item_no = SynRound.lineItem(crm_line_item_no);}

	@Column(name="contract_currency_code")
	private String contract_currency_code;
	public String getContract_currency_code() {return contract_currency_code;}
	public void setContract_currency_code(String contract_currency_code) {this.contract_currency_code = SynString.trim(contract_currency_code);}

	@Column(name="exch_rate")
	private double exch_rate;
	public double getExch_rate() {return exch_rate;}
	public void setExch_rate(double exch_rate) {this.exch_rate = SynRound.exchangeRate(exch_rate);}

	@Column(name="maint_contract_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date maint_contract_date;
	public Date getMaint_contract_date() {return maint_contract_date;}
	public void setMaint_contract_date(Date maint_contract_date) {this.maint_contract_date = maint_contract_date;}

	@Column(name="duration")
	private int duration;
	public int getDuration() {return duration;}
	public void setDuration(int duration) {this.duration = duration;}

	@Column(name="contract_start_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date contract_start_date;
	public Date getContract_start_date() {return contract_start_date;}
	public void setContract_start_date(Date contract_start_date) {this.contract_start_date = contract_start_date;}

	@Column(name="contract_expiry_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date contract_expiry_date;
	public Date getContract_expiry_date() {return contract_expiry_date;}
	public void setContract_expiry_date(Date contract_expiry_date) {this.contract_expiry_date = contract_expiry_date;}

	@Column(name="billing_frequency")
	private String billing_frequency;
	public String getBilling_frequency() {return billing_frequency;}
	public void setBilling_frequency(String billing_frequency) {this.billing_frequency = SynString.trim(billing_frequency);}

	@Column(name="billing_frequency_type")
	private String billing_frequency_type;
	public String getBilling_frequency_type() {return billing_frequency_type;}
	public void setBilling_frequency_type(String billing_frequency_type) {this.billing_frequency_type = SynString.trim(billing_frequency_type);}

	@Column(name="sales_tax_code")
	private String sales_tax_code;
	public String getSales_tax_code() {return sales_tax_code;}
	public void setSales_tax_code(String sales_tax_code) {this.sales_tax_code = SynString.trim(sales_tax_code);}

	@Column(name="sales_person_code")
	private String sales_person_code;
	public String getSales_person_code() {return sales_person_code;}
	public void setSales_person_code(String sales_person_code) {this.sales_person_code = SynString.trim(sales_person_code);}

	@Column(name="sales_hod_code")
	private String sales_hod_code;
	public String getSales_hod_code() {return sales_hod_code;}
	public void setSales_hod_code(String sales_hod_code) {this.sales_hod_code = SynString.trim(sales_hod_code);}

	@Column(name="sales_manager_code")
	private String sales_manager_code;
	public String getSales_manager_code() {return sales_manager_code;}
	public void setSales_manager_code(String sales_manager_code) {this.sales_manager_code = SynString.trim(sales_manager_code);}

	@Column(name="technical_person_code")
	private String technical_person_code;
	public String getTechnical_person_code() {return technical_person_code;}
	public void setTechnical_person_code(String technical_person_code) {this.technical_person_code = SynString.trim(technical_person_code);}

	@Column(name="sbu_code")
	private String sbu_code;
	public String getSbu_code() {return sbu_code;}
	public void setSbu_code(String sbu_code) {this.sbu_code = SynString.trim(sbu_code);}

	@Column(name="employee_code")
	private String employee_code;
	public String getEmployee_code() {return employee_code;}
	public void setEmployee_code(String employee_code) {this.employee_code = SynString.trim(employee_code);}

	@Column(name="service_customer_code")
	private String service_customer_code;
	public String getService_customer_code() {return service_customer_code;}
	public void setService_customer_code(String service_customer_code) {this.service_customer_code = SynString.trim(service_customer_code);}

	@Column(name="service_cust_address_code")
	private String service_cust_address_code;
	public String getService_cust_address_code() {return service_cust_address_code;}
	public void setService_cust_address_code(String service_cust_address_code) {this.service_cust_address_code = SynString.trim(service_cust_address_code);}

	@Column(name="service_cust_contact_code")
	private String service_cust_contact_code;
	public String getService_cust_contact_code() {return service_cust_contact_code;}
	public void setService_cust_contact_code(String service_cust_contact_code) {this.service_cust_contact_code = SynString.trim(service_cust_contact_code);}

	@Column(name="customer_po_no")
	private String customer_po_no;
	public String getCustomer_po_no() {return customer_po_no;}
	public void setCustomer_po_no(String customer_po_no) {this.customer_po_no = SynString.trim(customer_po_no);}

	@Column(name="reference_no")
	private String reference_no;
	public String getReference_no() {return reference_no;}
	public void setReference_no(String reference_no) {this.reference_no = SynString.trim(reference_no);}

	@Column(name="subject")
	private String subject;
	public String getSubject() {return subject;}
	public void setSubject(String subject) {this.subject = SynString.trim(subject);}

	@Column(name="internal_remarks")
	private String internal_remarks;
	public String getInternal_remarks() {return internal_remarks;}
	public void setInternal_remarks(String internal_remarks) {this.internal_remarks = SynString.trim(internal_remarks);}

	@Column(name="external_remarks")
	private String external_remarks;
	public String getExternal_remarks() {return external_remarks;}
	public void setExternal_remarks(String external_remarks) {this.external_remarks = SynString.trim(external_remarks);}

	@Column(name="total_disc_amt")
	private double total_disc_amt;
	public double getTotal_disc_amt() {return total_disc_amt;}
	public void setTotal_disc_amt(double total_disc_amt) {this.total_disc_amt = SynRound.amt(total_disc_amt);}

	@Column(name="billing_amt_per_freq")
	private double billing_amt_per_freq;
	public double getBilling_amt_per_freq() {return billing_amt_per_freq;}
	public void setBilling_amt_per_freq(double billing_amt_per_freq) {this.billing_amt_per_freq = SynRound.amt(billing_amt_per_freq);}

	@Column(name="total_pre_tax_amt")
	private double total_pre_tax_amt;
	public double getTotal_pre_tax_amt() {return total_pre_tax_amt;}
	public void setTotal_pre_tax_amt(double total_pre_tax_amt) {this.total_pre_tax_amt = SynRound.amt(total_pre_tax_amt);}

	@Column(name="total_pre_tax_home_amt")
	private double total_pre_tax_home_amt;
	public double getTotal_pre_tax_home_amt() {return total_pre_tax_home_amt;}
	public void setTotal_pre_tax_home_amt(double total_pre_tax_home_amt) {this.total_pre_tax_home_amt = SynRound.amt(total_pre_tax_home_amt);}

	@Column(name="total_sales_tax_amt")
	private double total_sales_tax_amt;
	public double getTotal_sales_tax_amt() {return total_sales_tax_amt;}
	public void setTotal_sales_tax_amt(double total_sales_tax_amt) {this.total_sales_tax_amt = SynRound.amt(total_sales_tax_amt);}

	@Column(name="total_sales_tax_home_amt")
	private double total_sales_tax_home_amt;
	public double getTotal_sales_tax_home_amt() {return total_sales_tax_home_amt;}
	public void setTotal_sales_tax_home_amt(double total_sales_tax_home_amt) {this.total_sales_tax_home_amt = SynRound.amt(total_sales_tax_home_amt);}

	@Column(name="total_after_tax_amt")
	private double total_after_tax_amt;
	public double getTotal_after_tax_amt() {return total_after_tax_amt;}
	public void setTotal_after_tax_amt(double total_after_tax_amt) {this.total_after_tax_amt = SynRound.amt(total_after_tax_amt);}

	@Column(name="total_after_tax_home_amt")
	private double total_after_tax_home_amt;
	public double getTotal_after_tax_home_amt() {return total_after_tax_home_amt;}
	public void setTotal_after_tax_home_amt(double total_after_tax_home_amt) {this.total_after_tax_home_amt = SynRound.amt(total_after_tax_home_amt);}

	@Column(name="total_inv_pre_tax_amt")
	private double total_inv_pre_tax_amt;
	public double getTotal_inv_pre_tax_amt() {return total_inv_pre_tax_amt;}
	public void setTotal_inv_pre_tax_amt(double total_inv_pre_tax_amt) {this.total_inv_pre_tax_amt = SynRound.amt(total_inv_pre_tax_amt);}

	@Column(name="total_inv_pre_tax_home_amt")
	private double total_inv_pre_tax_home_amt;
	public double getTotal_inv_pre_tax_home_amt() {return total_inv_pre_tax_home_amt;}
	public void setTotal_inv_pre_tax_home_amt(double total_inv_pre_tax_home_amt) {this.total_inv_pre_tax_home_amt = SynRound.amt(total_inv_pre_tax_home_amt);}

	@Column(name="total_inv_sales_tax_amt")
	private double total_inv_sales_tax_amt;
	public double getTotal_inv_sales_tax_amt() {return total_inv_sales_tax_amt;}
	public void setTotal_inv_sales_tax_amt(double total_inv_sales_tax_amt) {this.total_inv_sales_tax_amt = SynRound.amt(total_inv_sales_tax_amt);}

	@Column(name="total_inv_sales_tax_home_amt")
	private double total_inv_sales_tax_home_amt;
	public double getTotal_inv_sales_tax_home_amt() {return total_inv_sales_tax_home_amt;}
	public void setTotal_inv_sales_tax_home_amt(double total_inv_sales_tax_home_amt) {this.total_inv_sales_tax_home_amt = SynRound.amt(total_inv_sales_tax_home_amt);}

	@Column(name="total_inv_after_tax_amt")
	private double total_inv_after_tax_amt;
	public double getTotal_inv_after_tax_amt() {return total_inv_after_tax_amt;}
	public void setTotal_inv_after_tax_amt(double total_inv_after_tax_amt) {this.total_inv_after_tax_amt = SynRound.amt(total_inv_after_tax_amt);}

	@Column(name="total_inv_after_tax_home_amt")
	private double total_inv_after_tax_home_amt;
	public double getTotal_inv_after_tax_home_amt() {return total_inv_after_tax_home_amt;}
	public void setTotal_inv_after_tax_home_amt(double total_inv_after_tax_home_amt) {this.total_inv_after_tax_home_amt = SynRound.amt(total_inv_after_tax_home_amt);}

	@Column(name="total_crn_pre_tax_amt")
	private double total_crn_pre_tax_amt;
	public double getTotal_crn_pre_tax_amt() {return total_crn_pre_tax_amt;}
	public void setTotal_crn_pre_tax_amt(double total_crn_pre_tax_amt) {this.total_crn_pre_tax_amt = SynRound.amt(total_crn_pre_tax_amt);}

	@Column(name="total_crn_pre_tax_home_amt")
	private double total_crn_pre_tax_home_amt;
	public double getTotal_crn_pre_tax_home_amt() {return total_crn_pre_tax_home_amt;}
	public void setTotal_crn_pre_tax_home_amt(double total_crn_pre_tax_home_amt) {this.total_crn_pre_tax_home_amt = SynRound.amt(total_crn_pre_tax_home_amt);}

	@Column(name="total_crn_sales_tax_amt")
	private double total_crn_sales_tax_amt;
	public double getTotal_crn_sales_tax_amt() {return total_crn_sales_tax_amt;}
	public void setTotal_crn_sales_tax_amt(double total_crn_sales_tax_amt) {this.total_crn_sales_tax_amt = SynRound.amt(total_crn_sales_tax_amt);}

	@Column(name="total_crn_sales_tax_home_amt")
	private double total_crn_sales_tax_home_amt;
	public double getTotal_crn_sales_tax_home_amt() {return total_crn_sales_tax_home_amt;}
	public void setTotal_crn_sales_tax_home_amt(double total_crn_sales_tax_home_amt) {this.total_crn_sales_tax_home_amt = SynRound.amt(total_crn_sales_tax_home_amt);}

	@Column(name="total_crn_after_tax_amt")
	private double total_crn_after_tax_amt;
	public double getTotal_crn_after_tax_amt() {return total_crn_after_tax_amt;}
	public void setTotal_crn_after_tax_amt(double total_crn_after_tax_amt) {this.total_crn_after_tax_amt = SynRound.amt(total_crn_after_tax_amt);}

	@Column(name="total_crn_after_tax_home_amt")
	private double total_crn_after_tax_home_amt;
	public double getTotal_crn_after_tax_home_amt() {return total_crn_after_tax_home_amt;}
	public void setTotal_crn_after_tax_home_amt(double total_crn_after_tax_home_amt) {this.total_crn_after_tax_home_amt = SynRound.amt(total_crn_after_tax_home_amt);}

	@Column(name="nett_inv_pre_tax_amt")
	private double nett_inv_pre_tax_amt;
	public double getNett_inv_pre_tax_amt() {return nett_inv_pre_tax_amt;}
	public void setNett_inv_pre_tax_amt(double nett_inv_pre_tax_amt) {this.nett_inv_pre_tax_amt = SynRound.amt(nett_inv_pre_tax_amt);}

	@Column(name="nett_inv_pre_tax_home_amt")
	private double nett_inv_pre_tax_home_amt;
	public double getNett_inv_pre_tax_home_amt() {return nett_inv_pre_tax_home_amt;}
	public void setNett_inv_pre_tax_home_amt(double nett_inv_pre_tax_home_amt) {this.nett_inv_pre_tax_home_amt = SynRound.amt(nett_inv_pre_tax_home_amt);}

	@Column(name="nett_inv_sales_tax_amt")
	private double nett_inv_sales_tax_amt;
	public double getNett_inv_sales_tax_amt() {return nett_inv_sales_tax_amt;}
	public void setNett_inv_sales_tax_amt(double nett_inv_sales_tax_amt) {this.nett_inv_sales_tax_amt = SynRound.amt(nett_inv_sales_tax_amt);}

	@Column(name="nett_inv_sales_tax_home_amt")
	private double nett_inv_sales_tax_home_amt;
	public double getNett_inv_sales_tax_home_amt() {return nett_inv_sales_tax_home_amt;}
	public void setNett_inv_sales_tax_home_amt(double nett_inv_sales_tax_home_amt) {this.nett_inv_sales_tax_home_amt = SynRound.amt(nett_inv_sales_tax_home_amt);}

	@Column(name="nett_inv_after_tax_amt")
	private double nett_inv_after_tax_amt;
	public double getNett_inv_after_tax_amt() {return nett_inv_after_tax_amt;}
	public void setNett_inv_after_tax_amt(double nett_inv_after_tax_amt) {this.nett_inv_after_tax_amt = SynRound.amt(nett_inv_after_tax_amt);}

	@Column(name="nett_inv_after_tax_home_amt")
	private double nett_inv_after_tax_home_amt;
	public double getNett_inv_after_tax_home_amt() {return nett_inv_after_tax_home_amt;}
	public void setNett_inv_after_tax_home_amt(double nett_inv_after_tax_home_amt) {this.nett_inv_after_tax_home_amt = SynRound.amt(nett_inv_after_tax_home_amt);}

	@Column(name="enable_update_margin")
	private String enable_update_margin;
	public String getEnable_update_margin() {return enable_update_margin;}
	public void setEnable_update_margin(String enable_update_margin) {this.enable_update_margin = SynString.trim(enable_update_margin);}

	@Column(name="segment_1_code")
	private String segment_1_code;
	public String getSegment_1_code() {return segment_1_code;}
	public void setSegment_1_code(String segment_1_code) {this.segment_1_code = SynString.trim(segment_1_code);}

	@Column(name="segment_2_code")
	private String segment_2_code;
	public String getSegment_2_code() {return segment_2_code;}
	public void setSegment_2_code(String segment_2_code) {this.segment_2_code = SynString.trim(segment_2_code);}

	@Column(name="segment_3_code")
	private String segment_3_code;
	public String getSegment_3_code() {return segment_3_code;}
	public void setSegment_3_code(String segment_3_code) {this.segment_3_code = SynString.trim(segment_3_code);}

	@Column(name="segment_4_code")
	private String segment_4_code;
	public String getSegment_4_code() {return segment_4_code;}
	public void setSegment_4_code(String segment_4_code) {this.segment_4_code = SynString.trim(segment_4_code);}

	@Column(name="posted_by")
	private String posted_by;
	public String getPosted_by() {return posted_by;}
	public void setPosted_by(String posted_by) {this.posted_by = SynString.trim(posted_by);}

	@Column(name="posted_datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date posted_datetime;
	public Date getPosted_datetime() {return posted_datetime;}
	public void setPosted_datetime(Date posted_datetime) {this.posted_datetime = posted_datetime;}

	@Column(name="contract_type_code")
	private String contract_type_code;
	public String getContract_type_code() {return contract_type_code;}
	public void setContract_type_code(String contract_type_code) {this.contract_type_code = SynString.trim(contract_type_code);}

	@Column(name="professional_engineer_code")
	private String professional_engineer_code;
	public String getProfessional_engineer_code() {return professional_engineer_code;}
	public void setProfessional_engineer_code(String professional_engineer_code) {this.professional_engineer_code = SynString.trim(professional_engineer_code);}

	@Column(name="authorized_examiner_code")
	private String authorized_examiner_code;
	public String getAuthorized_examiner_code() {return authorized_examiner_code;}
	public void setAuthorized_examiner_code(String authorized_examiner_code) {this.authorized_examiner_code = SynString.trim(authorized_examiner_code);}

	@Column(name="termination_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date termination_date;
	public Date getTermination_date() {return termination_date;}
	public void setTermination_date(Date termination_date) {this.termination_date = termination_date;}

	@Column(name="variation_by")
	private String variation_by;
	public String getVariation_by() {return variation_by;}
	public void setVariation_by(String variation_by) {this.variation_by = SynString.trim(variation_by);}

	@Column(name="variation_datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date variation_datetime;
	public Date getVariation_datetime() {return variation_datetime;}
	public void setVariation_datetime(Date variation_datetime) {this.variation_datetime = variation_datetime;}

	@Column(name="est_total_cost")
	private double est_total_cost;
	public double getEst_total_cost() {return est_total_cost;}
	public void setEst_total_cost(double est_total_cost) {this.est_total_cost = est_total_cost;}

	@Column(name="est_total_cost_home")
	private double est_total_cost_home;
	public double getEst_total_cost_home() {return est_total_cost_home;}
	public void setEst_total_cost_home(double est_total_cost_home) {this.est_total_cost_home = est_total_cost_home;}

	@Column(name="est_profit")
	private double est_profit;
	public double getEst_profit() {return est_profit;}
	public void setEst_profit(double est_profit) {this.est_profit = est_profit;}

	@Column(name="est_profit_home")
	private double est_profit_home;
	public double getEst_profit_home() {return est_profit_home;}
	public void setEst_profit_home(double est_profit_home) {this.est_profit_home = est_profit_home;}

	@Column(name="created_by")
	private String created_by;
	public String getCreated_by() {return created_by;}
	public void setCreated_by(String created_by) {this.created_by = SynString.trim(created_by);}

	@Column(name="created_datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_datetime;
	public Date getCreated_datetime() {return created_datetime;}
	public void setCreated_datetime(Date created_datetime) {this.created_datetime = created_datetime;}

	@Column(name="last_updated_by")
	private String last_updated_by;
	public String getLast_updated_by() {return last_updated_by;}
	public void setLast_updated_by(String last_updated_by) {this.last_updated_by = SynString.trim(last_updated_by);}

	@Column(name="last_updated_datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date last_updated_datetime;
	public Date getLast_updated_datetime() {return last_updated_datetime;}
	public void setLast_updated_datetime(Date last_updated_datetime) {this.last_updated_datetime = last_updated_datetime;}

	@Column(name="object_version")
	private int object_version;
	public int getObject_version() {return object_version;}
	public void setObject_version(int object_version) {this.object_version = object_version;}

	@Column(name="jom_pk_no")
	private int jom_pk_no;
	public int getJom_pk_no() {return jom_pk_no;}
	public void setJom_pk_no(int jom_pk_no) {this.jom_pk_no = jom_pk_no;}

	@Column(name="last_modified_by")
	private String last_modified_by;
	public String getLast_modified_by() {return last_modified_by;}
	public void setLast_modified_by(String last_modified_by) {this.last_modified_by = SynString.trim(last_modified_by);}

	@Column(name="last_modified_datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date last_modified_datetime;
	public Date getLast_modified_datetime() {return last_modified_datetime;}
	public void setLast_modified_datetime(Date last_modified_datetime) {this.last_modified_datetime = last_modified_datetime;}

	// Foreign keys (@ManyToOne relationships)

	@ManyToOne
	@JoinColumn(name="jom_pk_no", referencedColumnName="jom_pk_no", insertable = false, updatable = false)
	private Jm_jom_ost_hdr jm_jom_ost_hdr;	
	public Jm_jom_ost_hdr getJm_jom_ost_hdr() {return this.jm_jom_ost_hdr;}
	public void setJm_jom_ost_hdr(Jm_jom_ost_hdr jm_jom_ost_hdr) {this.jm_jom_ost_hdr = jm_jom_ost_hdr;}

	@ManyToOne
	@JoinColumn(name="contract_type_code", referencedColumnName="contract_type_code", insertable = false, updatable = false)
	private Mt_contract_type mt_contract_type;	
	public Mt_contract_type getMt_contract_type() {return this.mt_contract_type;}
	public void setMt_contract_type(Mt_contract_type mt_contract_type) {this.mt_contract_type = mt_contract_type;}

	@ManyToOne
	@JoinColumn(name="contract_currency_code", referencedColumnName="currency_code", insertable = false, updatable = false)
	private Mt_currency mt_currency;	
	public Mt_currency getMt_currency() {return this.mt_currency;}
	public void setMt_currency(Mt_currency mt_currency) {this.mt_currency = mt_currency;}

	@ManyToOne
	@JoinColumn(name="sales_hod_code", referencedColumnName="employee_code", insertable = false, updatable = false)
	private Mt_employee mt_employee;	
	public Mt_employee getMt_employee() {return this.mt_employee;}
	public void setMt_employee(Mt_employee mt_employee) {this.mt_employee = mt_employee;}

	@ManyToOne
	@JoinColumn(name="sales_manager_code", referencedColumnName="employee_code", insertable = false, updatable = false)
	private Mt_employee mt_employee;	
	public Mt_employee getMt_employee() {return this.mt_employee;}
	public void setMt_employee(Mt_employee mt_employee) {this.mt_employee = mt_employee;}

	@ManyToOne
	@JoinColumn(name="technical_person_code", referencedColumnName="employee_code", insertable = false, updatable = false)
	private Mt_employee mt_employee;	
	public Mt_employee getMt_employee() {return this.mt_employee;}
	public void setMt_employee(Mt_employee mt_employee) {this.mt_employee = mt_employee;}

	@ManyToOne
	@JoinColumn(name="sales_person_code", referencedColumnName="employee_code", insertable = false, updatable = false)
	private Mt_employee mt_employee;	
	public Mt_employee getMt_employee() {return this.mt_employee;}
	public void setMt_employee(Mt_employee mt_employee) {this.mt_employee = mt_employee;}

	@ManyToOne
	@JoinColumn(name="party_code", referencedColumnName="party_code", insertable = false, updatable = false)
	private Mt_party mt_party;	
	public Mt_party getMt_party() {return this.mt_party;}
	public void setMt_party(Mt_party mt_party) {this.mt_party = mt_party;}

	@ManyToOne
	@JoinColumn(name="service_customer_code", referencedColumnName="party_code", insertable = false, updatable = false)
	private Mt_party mt_party;	
	public Mt_party getMt_party() {return this.mt_party;}
	public void setMt_party(Mt_party mt_party) {this.mt_party = mt_party;}

	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="party_code", referencedColumnName="party_code", insertable = false, updatable = false),
		@JoinColumn(name="party_address_code", referencedColumnName="party_address_code", insertable = false, updatable = false)})
	private Mt_party_address mt_party_address;	
	public Mt_party_address getMt_party_address() {return this.mt_party_address;}
	public void setMt_party_address(Mt_party_address mt_party_address) {this.mt_party_address = mt_party_address;}

	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="service_customer_code", referencedColumnName="party_code", insertable = false, updatable = false),
		@JoinColumn(name="service_cust_address_code", referencedColumnName="party_address_code", insertable = false, updatable = false)})
	private Mt_party_address mt_party_address;	
	public Mt_party_address getMt_party_address() {return this.mt_party_address;}
	public void setMt_party_address(Mt_party_address mt_party_address) {this.mt_party_address = mt_party_address;}

	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="party_code", referencedColumnName="party_code", insertable = false, updatable = false),
		@JoinColumn(name="party_contact_code", referencedColumnName="party_contact_code", insertable = false, updatable = false)})
	private Mt_party_contact mt_party_contact;	
	public Mt_party_contact getMt_party_contact() {return this.mt_party_contact;}
	public void setMt_party_contact(Mt_party_contact mt_party_contact) {this.mt_party_contact = mt_party_contact;}

	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="service_customer_code", referencedColumnName="party_code", insertable = false, updatable = false),
		@JoinColumn(name="service_cust_contact_code", referencedColumnName="party_contact_code", insertable = false, updatable = false)})
	private Mt_party_contact mt_party_contact;	
	public Mt_party_contact getMt_party_contact() {return this.mt_party_contact;}
	public void setMt_party_contact(Mt_party_contact mt_party_contact) {this.mt_party_contact = mt_party_contact;}

	@ManyToOne
	@JoinColumn(name="sales_tax_code", referencedColumnName="sales_tax_code", insertable = false, updatable = false)
	private Mt_sales_tax mt_sales_tax;	
	public Mt_sales_tax getMt_sales_tax() {return this.mt_sales_tax;}
	public void setMt_sales_tax(Mt_sales_tax mt_sales_tax) {this.mt_sales_tax = mt_sales_tax;}

	@ManyToOne
	@JoinColumn(name="sbu_code", referencedColumnName="sbu_code", insertable = false, updatable = false)
	private Mt_sbu mt_sbu;	
	public Mt_sbu getMt_sbu() {return this.mt_sbu;}
	public void setMt_sbu(Mt_sbu mt_sbu) {this.mt_sbu = mt_sbu;}
	
	// Inverse for foreign keys (@OneToMany relationships)


	@OneToMany(mappedBy = "mc_mct_ost_hdr", cascade = CascadeType.ALL)
	private List<Mc_ar_crn_det> mc_ar_crn_dets = new ArrayList<Mc_ar_crn_det>();	
	public List<Mc_ar_crn_det> getMc_ar_crn_dets() { return this.mc_ar_crn_dets;}
	public void setMc_ar_crn_dets(List<Mc_ar_crn_det> mc_ar_crn_dets) {this.mc_ar_crn_dets = mc_ar_crn_dets;}

	@OneToMany(mappedBy = "mc_mct_ost_hdr", cascade = CascadeType.ALL)
	private List<Mc_ar_crn_oa> mc_ar_crn_oas = new ArrayList<Mc_ar_crn_oa>();	
	public List<Mc_ar_crn_oa> getMc_ar_crn_oas() { return this.mc_ar_crn_oas;}
	public void setMc_ar_crn_oas(List<Mc_ar_crn_oa> mc_ar_crn_oas) {this.mc_ar_crn_oas = mc_ar_crn_oas;}

	@OneToMany(mappedBy = "mc_mct_ost_hdr", cascade = CascadeType.ALL)
	private List<Mc_ar_inv_det> mc_ar_inv_dets = new ArrayList<Mc_ar_inv_det>();	
	public List<Mc_ar_inv_det> getMc_ar_inv_dets() { return this.mc_ar_inv_dets;}
	public void setMc_ar_inv_dets(List<Mc_ar_inv_det> mc_ar_inv_dets) {this.mc_ar_inv_dets = mc_ar_inv_dets;}

	@OneToMany(mappedBy = "mc_mct_ost_hdr", cascade = CascadeType.ALL)
	private List<Mc_ar_inv_hdr> mc_ar_inv_hdrs = new ArrayList<Mc_ar_inv_hdr>();	
	public List<Mc_ar_inv_hdr> getMc_ar_inv_hdrs() { return this.mc_ar_inv_hdrs;}
	public void setMc_ar_inv_hdrs(List<Mc_ar_inv_hdr> mc_ar_inv_hdrs) {this.mc_ar_inv_hdrs = mc_ar_inv_hdrs;}

	@OneToMany(mappedBy = "mc_mct_ost_hdr", cascade = CascadeType.ALL)
	private List<Mc_ar_inv_oa> mc_ar_inv_oas = new ArrayList<Mc_ar_inv_oa>();	
	public List<Mc_ar_inv_oa> getMc_ar_inv_oas() { return this.mc_ar_inv_oas;}
	public void setMc_ar_inv_oas(List<Mc_ar_inv_oa> mc_ar_inv_oas) {this.mc_ar_inv_oas = mc_ar_inv_oas;}

	@OneToMany(mappedBy = "mc_mct_ost_hdr", cascade = CascadeType.ALL)
	private List<Mc_mct_asset_so_hdr> mc_mct_asset_so_hdrs = new ArrayList<Mc_mct_asset_so_hdr>();	
	public List<Mc_mct_asset_so_hdr> getMc_mct_asset_so_hdrs() { return this.mc_mct_asset_so_hdrs;}
	public void setMc_mct_asset_so_hdrs(List<Mc_mct_asset_so_hdr> mc_mct_asset_so_hdrs) {this.mc_mct_asset_so_hdrs = mc_mct_asset_so_hdrs;}

	@OneToMany(mappedBy = "mc_mct_ost_hdr", cascade = CascadeType.ALL)
	private List<Mc_mct_do_hdr> mc_mct_do_hdrs = new ArrayList<Mc_mct_do_hdr>();	
	public List<Mc_mct_do_hdr> getMc_mct_do_hdrs() { return this.mc_mct_do_hdrs;}
	public void setMc_mct_do_hdrs(List<Mc_mct_do_hdr> mc_mct_do_hdrs) {this.mc_mct_do_hdrs = mc_mct_do_hdrs;}

	@OneToMany(mappedBy = "mc_mct_ost_hdr", cascade = CascadeType.ALL)
	private List<Mc_mct_do_track> mc_mct_do_tracks = new ArrayList<Mc_mct_do_track>();	
	public List<Mc_mct_do_track> getMc_mct_do_tracks() { return this.mc_mct_do_tracks;}
	public void setMc_mct_do_tracks(List<Mc_mct_do_track> mc_mct_do_tracks) {this.mc_mct_do_tracks = mc_mct_do_tracks;}

	@OneToMany(mappedBy = "mc_mct_ost_hdr", cascade = CascadeType.ALL)
	private List<Mc_mct_ost_det> mc_mct_ost_dets = new ArrayList<Mc_mct_ost_det>();	
	public List<Mc_mct_ost_det> getMc_mct_ost_dets() { return this.mc_mct_ost_dets;}
	public void setMc_mct_ost_dets(List<Mc_mct_ost_det> mc_mct_ost_dets) {this.mc_mct_ost_dets = mc_mct_ost_dets;}

	@OneToMany(mappedBy = "mc_mct_ost_hdr", cascade = CascadeType.ALL)
	private List<Mc_mct_ost_pymt_info> mc_mct_ost_pymt_infos = new ArrayList<Mc_mct_ost_pymt_info>();	
	public List<Mc_mct_ost_pymt_info> getMc_mct_ost_pymt_infos() { return this.mc_mct_ost_pymt_infos;}
	public void setMc_mct_ost_pymt_infos(List<Mc_mct_ost_pymt_info> mc_mct_ost_pymt_infos) {this.mc_mct_ost_pymt_infos = mc_mct_ost_pymt_infos;}

	@OneToMany(mappedBy = "mc_mct_ost_hdr", cascade = CascadeType.ALL)
	private List<Mc_mct_ost_tax> mc_mct_ost_taxs = new ArrayList<Mc_mct_ost_tax>();	
	public List<Mc_mct_ost_tax> getMc_mct_ost_taxs() { return this.mc_mct_ost_taxs;}
	public void setMc_mct_ost_taxs(List<Mc_mct_ost_tax> mc_mct_ost_taxs) {this.mc_mct_ost_taxs = mc_mct_ost_taxs;}

	@OneToMany(mappedBy = "mc_mct_ost_hdr", cascade = CascadeType.ALL)
	private List<Mc_mct_so_hdr> mc_mct_so_hdrs = new ArrayList<Mc_mct_so_hdr>();	
	public List<Mc_mct_so_hdr> getMc_mct_so_hdrs() { return this.mc_mct_so_hdrs;}
	public void setMc_mct_so_hdrs(List<Mc_mct_so_hdr> mc_mct_so_hdrs) {this.mc_mct_so_hdrs = mc_mct_so_hdrs;}

	@OneToMany(mappedBy = "mc_mct_ost_hdr", cascade = CascadeType.ALL)
	private List<Mc_quo_hst_hdr> mc_quo_hst_hdrs = new ArrayList<Mc_quo_hst_hdr>();	
	public List<Mc_quo_hst_hdr> getMc_quo_hst_hdrs() { return this.mc_quo_hst_hdrs;}
	public void setMc_quo_hst_hdrs(List<Mc_quo_hst_hdr> mc_quo_hst_hdrs) {this.mc_quo_hst_hdrs = mc_quo_hst_hdrs;}

	@OneToMany(mappedBy = "mc_mct_ost_hdr", cascade = CascadeType.ALL)
	private List<Mc_quo_new_hdr> mc_quo_new_hdrs = new ArrayList<Mc_quo_new_hdr>();	
	public List<Mc_quo_new_hdr> getMc_quo_new_hdrs() { return this.mc_quo_new_hdrs;}
	public void setMc_quo_new_hdrs(List<Mc_quo_new_hdr> mc_quo_new_hdrs) {this.mc_quo_new_hdrs = mc_quo_new_hdrs;}

	@OneToMany(mappedBy = "mc_mct_ost_hdr", cascade = CascadeType.ALL)
	private List<Mc_usage> mc_usages = new ArrayList<Mc_usage>();	
	public List<Mc_usage> getMc_usages() { return this.mc_usages;}
	public void setMc_usages(List<Mc_usage> mc_usages) {this.mc_usages = mc_usages;}

	@OneToMany(mappedBy = "mc_mct_ost_hdr", cascade = CascadeType.ALL)
	private List<Sv_order_hdr> sv_order_hdrs = new ArrayList<Sv_order_hdr>();	
	public List<Sv_order_hdr> getSv_order_hdrs() { return this.sv_order_hdrs;}
	public void setSv_order_hdrs(List<Sv_order_hdr> sv_order_hdrs) {this.sv_order_hdrs = sv_order_hdrs;}

	@OneToMany(mappedBy = "mc_mct_ost_hdr", cascade = CascadeType.ALL)
	private List<Sv_profile_hdr> sv_profile_hdrs = new ArrayList<Sv_profile_hdr>();	
	public List<Sv_profile_hdr> getSv_profile_hdrs() { return this.sv_profile_hdrs;}
	public void setSv_profile_hdrs(List<Sv_profile_hdr> sv_profile_hdrs) {this.sv_profile_hdrs = sv_profile_hdrs;}

	// Additional Constructors if any

	// Other Methods if any
	
	// Constructors

	public Mc_mct_ost_hdr() {this.pk = new Pk();}
	public Mc_mct_ost_hdr(Pk pk) {this.pk = pk;}
	
	// Embedded primary key class
	@Embeddable
	public static class Pk extends AbstractPk {
		@Column(name="maint_contract_no")
		private String maint_contract_no;
		public String getMaint_contract_no() {return maint_contract_no;}
		public void setMaint_contract_no(String maint_contract_no) {this.maint_contract_no = SynString.idTrim(maint_contract_no);}

		@Column(name="renewal_no")
		private int renewal_no;
		public int getRenewal_no() {return renewal_no;}
		public void setRenewal_no(int renewal_no) {this.renewal_no = renewal_no;}


		public Pk() {}		
		public Pk(String maint_contract_no, int renewal_no) {
			this.setMaint_contract_no(maint_contract_no);
			this.setRenewal_no(renewal_no);
		}
	}
}