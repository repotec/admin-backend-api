package com.ntg.adm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


/**
 * The persistent class for the ADM_APPLICATIONS_TL database table.
 * 
 */
@Entity
@Table(name="ADM_APPLICATIONS_TL")
@NamedQuery(name="AdmApplicationsTl.findAll", query="SELECT a FROM AdmApplicationsTl a")
public class AdmApplicationsTl implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="APPLICATIONS_TL_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appTlGen")
	@SequenceGenerator(name = "appTlGen", sequenceName = "APPLICATION_TL_SEQ", allocationSize = 1)
	private long applicartionsTlId;
	
	@Column(name="APPLICATION_DISPLAY_NAME")
	private String applicationDisplayName;
	
	@Column(name="APPLICATION_ID", insertable = false, updatable = false)
	private java.math.BigDecimal applicationId;

	@Column(name="LANGUAGE_CODE")
	private String languageCode;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="APPLICATION_ID")
	@JsonBackReference
	private AdmApplication admApplication;

	public String getApplicationDisplayName() {
		return this.applicationDisplayName;
	}

	public void setApplicationDisplayName(String applicationDisplayName) {
		this.applicationDisplayName = applicationDisplayName;
	}

	public java.math.BigDecimal getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(java.math.BigDecimal applicationId) {
		this.applicationId = applicationId;
	}

	public String getLanguageCode() {
		return this.languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public long getApplicartionsTlId() {
		return applicartionsTlId;
	}

	public void setApplicartionsTlId(long applicartionsTlId) {
		this.applicartionsTlId = applicartionsTlId;
	}

	public AdmApplication getAdmApplication() {
		return admApplication;
	}

	public void setAdmApplication(AdmApplication admApplication) {
		this.admApplication = admApplication;
	}

}