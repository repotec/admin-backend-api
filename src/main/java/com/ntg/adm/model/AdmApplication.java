package com.ntg.adm.model;

import java.io.Serializable;
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

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ntg.adm.base.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name="ADM_APPLICATIONS")
@NamedQuery(name="AdmApplication.findAll", query="SELECT a FROM AdmApplication a")
@NamedQuery(name="AdmApplication.findAllByNameByNamedQuery", query="SELECT a FROM AdmApplication a where lower(a.applicationName) like concat('%', lower(:applicationName) ,'%') ")
@DynamicUpdate
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Where(clause = "IS_DELETED = '0'")
public class AdmApplication extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="APPLICATION_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appGenerator")
	@SequenceGenerator(name = "appGenerator", sequenceName = "APPLICATION_SEQ", allocationSize = 1)
	private long applicationId;
	
	@Column(name="APPLICATION_NAME")
	private String applicationName;

	@Column(name="APPLICATION_URL")
	private String applicationUrl;

	@Column(name="IMAGE")
	private String image;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@OneToMany(mappedBy="admApplication", cascade = CascadeType.PERSIST)
	@JsonManagedReference
	@BatchSize(size = 2)
	private List<AdmApplicationText> admApplicationText;
	
	@Column(unique = true)
	public String getApplicationName() {
		return applicationName;
	}
}