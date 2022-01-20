package com.ntg.adm.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="ADM_USERS")
@Setter
@Getter
@NoArgsConstructor
@NamedEntityGraph(name = "userPrinciple-graph",
                  attributeNodes = @NamedAttributeNode(value = "admUserRoles", subgraph = "role-subgraph"),
							subgraphs = @NamedSubgraph(name = "role-subgraph", attributeNodes = @NamedAttributeNode("admRole")))
public class AdmUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "user-seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "user-seq", sequenceName = "USER_SEQ", allocationSize = 1)
	@Column(name="USER_ID")
	private long userId;

	@Column(name="USER_NAME")
	private String userName;

	@Column(name="PASSWORD")
	private String password;

	@Column(name="PASSWORD_FIRST")
	private String passwordFirst;

	@Column(name="TITLE")
	private BigDecimal title;

	@Column(name="FIRST_NAME")
	private String firstName;

	@Column(name="MIDDLE_NAME")
	private String middleName;

	@Column(name="LAST_NAME")
	private String lastName;

	@Temporal(TemporalType.DATE)
	@Column(name="EFFECTIVE_END_DATE")
	private Date effectiveEndDate;

	@Temporal(TemporalType.DATE)
	@Column(name="EFFECTIVE_START_DATE")
	private Date effectiveStartDate;

	@Column(name="PHONE")
	private String phone;

	@Column(name="CELL_PHONE")
	private String cellPhone;

	@Column(name="EMAIL")
	private String email;

	@Column(name="FAX")
	private String fax;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_LOGIN_DATE")
	private Date lastLoginDate;

	@Column(name="PERSON_ID")
	private BigDecimal personId;

	@Column(name="SUPPLIER_ID")
	private BigDecimal supplierId;

	@Column(name="USER_TYPE_ID")
	private BigDecimal userTypeId;

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

	@OneToMany(mappedBy="admUser", fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<AdmUserRole> admUserRoles;
}