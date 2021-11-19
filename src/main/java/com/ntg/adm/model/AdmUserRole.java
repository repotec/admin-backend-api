package com.ntg.adm.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="ADM_USER_ROLES")
@Setter
@Getter
@NoArgsConstructor
public class AdmUserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USER_ROLE_ID")
	private long userRoleId;

	@Column(name="CREATED_BY")
	private BigDecimal createdBy;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATION_DATE")
	private Date creationDate;

	@Temporal(TemporalType.DATE)
	@Column(name="EFFECTIVE_END_DATE")
	private Date effectiveEndDate;

	@Temporal(TemporalType.DATE)
	@Column(name="EFFECTIVE_START_DATE")
	private Date effectiveStartDate;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_UPDATE_DATE")
	private Date lastUpdateDate;

	@Column(name="LAST_UPDATED_BY")
	private BigDecimal lastUpdatedBy;

	@ManyToOne
	@JoinColumn(name="ROLE_ID")
	private AdmRole admRole;

	@ManyToOne
	@JoinColumn(name="USER_ID")
	@JsonBackReference
	private AdmUser admUser;
}