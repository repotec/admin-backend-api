package com.ntg.adm.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="ADM_FUNCTION_ATTRIBUTES")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdmFunctionAttribute implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="ATTRIBUTE_ID")
    private long attributeId;

    @Column(name="ATTRIBUTE_NAME")
    private String attributeName;

    @Column(name="CREATED_BY")
    private BigDecimal createdBy;

    @Temporal(TemporalType.DATE)
    @Column(name="CREATION_DATE")
    private Date creationDate;

    @Column(name="ELEMENT_ID")
    private String elementId;

    @Temporal(TemporalType.DATE)
    @Column(name="LAST_UPDATE_DATE")
    private Date lastUpdateDate;

    @Column(name="LAST_UPDATED_BY")
    private BigDecimal lastUpdatedBy;

    @Column(name="FUNCTION_ID", insertable = false, updatable = false)
    private BigDecimal functionId;

    //bi-directional many-to-one association to AdmFunction
    @ManyToOne
    @JoinColumn(name="FUNCTION_ID")
    @JsonBackReference
    private AdmFunction admFunction;
}