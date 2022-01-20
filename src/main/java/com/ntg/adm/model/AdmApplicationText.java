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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * The persistent class for the ADM_APPLICATIONS_TL database table.
 * 
 */
@Entity
@Table(name="ADM_APPLICATION_TEXTS")
@NamedQuery(name="AdmApplicationText.findAll", query="SELECT a FROM AdmApplicationText a")
@Setter
@Getter
@NoArgsConstructor
public class AdmApplicationText implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="APPLICATION_TEXT_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appTlGen")
	@SequenceGenerator(name = "appTlGen", sequenceName = "APPLICATIONS_TEXT_SEQ", allocationSize = 1)
	private long applicartionTextId;
	
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
}