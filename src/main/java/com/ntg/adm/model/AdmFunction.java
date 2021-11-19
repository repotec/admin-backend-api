package com.ntg.adm.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the ADM_FUNCTIONS database table.
 * 
 */
@Entity
@Table(name="ADM_FUNCTIONS")
@NamedQuery(name="AdmFunction.findAll", query="SELECT a FROM AdmFunction a")
public class AdmFunction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="FUNCTION_ID")
	private long functionId;

	@Column(name="APPLICATION_ID")
	private BigDecimal applicationId;

	@Column(name="CREATED_BY")
	private BigDecimal createdBy;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATION_DATE")
	private Date creationDate;

	private String description;

	@Column(name="FUNCTION_DEFAULT_NAME")
	private String functionDefaultName;

	@Column(name="FUNCTION_ICON")
	private String functionIcon;

	@Column(name="FUNCTION_NAME")
	private String functionName;

	@Column(name="FUNCTION_PATH")
	private String functionPath;

	@Column(name="IS_BPM_FUNCTION")
	private String isBpmFunction;

	@Column(name="IS_SUB_FUNCTION")
	private String isSubFunction;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_UPDATE_DATE")
	private Date lastUpdateDate;

	@Column(name="LAST_UPDATE_LOGIN")
	private BigDecimal lastUpdateLogin;

	@Column(name="LAST_UPDATED_BY")
	private BigDecimal lastUpdatedBy;

	@Column(name="PARENT_FUNCTION_ID")
	private BigDecimal parentFunctionId;

	@Column(name="SECURITY_PATH")
	private String securityPath;

	public AdmFunction() {
	}

	public long getFunctionId() {
		return this.functionId;
	}

	public void setFunctionId(long functionId) {
		this.functionId = functionId;
	}

	public BigDecimal getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(BigDecimal applicationId) {
		this.applicationId = applicationId;
	}

	public BigDecimal getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(BigDecimal createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFunctionDefaultName() {
		return this.functionDefaultName;
	}

	public void setFunctionDefaultName(String functionDefaultName) {
		this.functionDefaultName = functionDefaultName;
	}

	public String getFunctionIcon() {
		return this.functionIcon;
	}

	public void setFunctionIcon(String functionIcon) {
		this.functionIcon = functionIcon;
	}

	public String getFunctionName() {
		return this.functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getFunctionPath() {
		return this.functionPath;
	}

	public void setFunctionPath(String functionPath) {
		this.functionPath = functionPath;
	}

	public String getIsBpmFunction() {
		return this.isBpmFunction;
	}

	public void setIsBpmFunction(String isBpmFunction) {
		this.isBpmFunction = isBpmFunction;
	}

	public String getIsSubFunction() {
		return this.isSubFunction;
	}

	public void setIsSubFunction(String isSubFunction) {
		this.isSubFunction = isSubFunction;
	}

	public Date getLastUpdateDate() {
		return this.lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public BigDecimal getLastUpdateLogin() {
		return this.lastUpdateLogin;
	}

	public void setLastUpdateLogin(BigDecimal lastUpdateLogin) {
		this.lastUpdateLogin = lastUpdateLogin;
	}

	public BigDecimal getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	public void setLastUpdatedBy(BigDecimal lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public BigDecimal getParentFunctionId() {
		return this.parentFunctionId;
	}

	public void setParentFunctionId(BigDecimal parentFunctionId) {
		this.parentFunctionId = parentFunctionId;
	}

	public String getSecurityPath() {
		return this.securityPath;
	}

	public void setSecurityPath(String securityPath) {
		this.securityPath = securityPath;
	}

}