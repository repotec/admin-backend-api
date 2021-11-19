package com.ntg.adm.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ntg.adm.validation.annotation.ApplicationNamesChecker;
import com.ntg.adm.validation.annotation.Prefix;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="ADM_APPLICATIONS")
@NamedQuery(name="AdmApplication.findAll", query="SELECT a FROM AdmApplication a")
@NamedQuery(name="AdmApplication.findAllByNameByNamedQuery", query="SELECT a FROM AdmApplication a where lower(a.applicationName) like concat('%', lower(:applicationName) ,'%') ")
@DynamicUpdate
@Setter
@Getter
@NoArgsConstructor
public class AdmApplication implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="APPLICATION_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appGenerator")
	@SequenceGenerator(name = "appGenerator", sequenceName = "APPLICATION_SEQ", allocationSize = 1)
	@ApiModelProperty(notes = "The database generated aplication ID")
	private long applicationId;

	//@Prefix(value = "app", message = "Please enter a valid prefix {value}")
	@NotNull(message = "application name cannot be empty")
	@Size(max=50, min=5, message = "application name length must be great then {min} and less than {max}")
	@Column(name="APPLICATION_NAME")
	private String applicationName;

	@Column(name="APPLICATION_URL")
	private String applicationUrl;

	@Column(name="IMAGE")
	private String image;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Column(name="CREATED_BY")
	private BigDecimal createdBy;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATION_DATE")
	@Past(message = "create date must be in the past only.")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date creationDate;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_UPDATE_DATE")
	private Date lastUpdateDate;

	@Column(name="LAST_UPDATED_BY")
	private BigDecimal lastUpdatedBy;

	@OneToMany(mappedBy="admApplication", cascade = CascadeType.PERSIST)
	@JsonManagedReference
	@BatchSize(size = 2)
	private List<AdmApplicationsTl> admApplicationsTl;
}