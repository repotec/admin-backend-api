package com.ntg.adm.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="ADM_ROLES")
@Setter
@Getter
@NoArgsConstructor
public class AdmRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ROLE_ID")
	private long roleId;

	@Column(name="APPLICATION_ID")
	private BigDecimal applicationId;

	@Column(name="MENU_ID")
	private BigDecimal menuId;

	@Column(name="ROLE_NAME")
	private String roleName;

	@Temporal(TemporalType.DATE)
	@Column(name="EFFECTIVE_START_DATE")
	private Date effectiveStartDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="EFFECTIVE_END_DATE")
	private Date effectiveEndDate;
	
	@Column(name="CREATED_BY")
	private BigDecimal createdBy;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATION_DATE")
	private Date creationDate;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_UPDATE_DATE")
	private Date lastUpdateDate;

	@Column(name="LAST_UPDATED_BY")
	private BigDecimal lastUpdatedBy;
	
	@OneToMany(mappedBy = "admRole", cascade = CascadeType.PERSIST)
	@JsonManagedReference
	@BatchSize(size = 2)
	private List<AdmRolesTl> admRolesTl;
}