package com.ntg.adm.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
@Where(clause = "IS_DELETED = '0'")
@Setter
@Getter
@NoArgsConstructor
public abstract class AdminBaseEntity {

	@CreatedBy
	@Column(name="CREATED_BY")
	private Long createdBy;

	@CreatedDate
	@Temporal(TemporalType.DATE)
	@Column(name="CREATED_DATE")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date createdDate;

	@LastModifiedBy
	@Column(name="LAST_MODIFIED_BY")
	private Long lastModifiedBy;
	
	@LastModifiedDate
	@Temporal(TemporalType.DATE)
	@Column(name="LAST_MODIFIED_DATE")
	private Date lastUpdateDate;
	
	@Column(name = "IS_DELETED")
	private char isDeleted;
}
