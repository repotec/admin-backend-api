package com.ntg.adm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.ntg.adm.base.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ADM_CONTENT_STORAGE_PROPERTIES")
@Getter
@Setter
@NoArgsConstructor
public class AdmContentStorageProperties extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contentStoragePropertiesSeq")
	@SequenceGenerator(name = "contentStoragePropertiesSeq", sequenceName = "ADM_CONTENT_STORAGE_PROP_SEQ", allocationSize = 1)
	@Column(name = "CONTENT_ID")
	Long contentId;
	
	@Column(name = "CONTENT_NAME")
	String contentName;
	
	@Column(name = "CONTENT_FORMAT")
	String contentFormat;
	
	@Column(name = "CONTENT_DIRECTORY")
	String contentDirectory;
	
	@Column(name = "CONTENT_TYPE")
	String contentType;
}
