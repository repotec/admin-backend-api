package com.ntg.adm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


/**
 * The persistent class for the ADM_ROLES_TL database table.
 * 
 */
@Entity
@Table(name="ADM_ROLES_TL")
@NamedQuery(name="AdmRolesTl.findAll", query="SELECT a FROM AdmRolesTl a")
public class AdmRolesTl implements Serializable {
	private static final long serialVersionUID = 1L;

	public AdmRolesTl() {
	}
	
	@Id
	@Column(name="ROLE_TL_ID")
	private long roleTlId;

	@Column(name="ROLE_DISPLAY_NAME")
	private String roleDisplayName;

	@Column(name="ROLE_ID", insertable = false, updatable = false)
	private java.math.BigDecimal roleId;

	@Column(name="LANGUAGE_CODE")
	private String languageCode;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ROLE_ID")
	@JsonBackReference
	private AdmRole admRole;

	public long getRoleTlId() {
		return roleTlId;
	}

	public void setRoleTlId(long roleTlId) {
		this.roleTlId = roleTlId;
	}
	
	public String getLanguageCode() {
		return this.languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getRoleDisplayName() {
		return this.roleDisplayName;
	}

	public void setRoleDisplayName(String roleDisplayName) {
		this.roleDisplayName = roleDisplayName;
	}

	public java.math.BigDecimal getRoleId() {
		return this.roleId;
	}

	public void setRoleId(java.math.BigDecimal roleId) {
		this.roleId = roleId;
	}

}