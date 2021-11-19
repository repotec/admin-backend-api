package com.ntg.adm.dao.specification;

import com.ntg.adm.model.AdmApplication;
import com.sun.javafx.binding.StringFormatter;
import org.springframework.data.jpa.domain.Specification;

import java.text.MessageFormat;

public class ApplicationSpecification {
    public static Specification<AdmApplication> nameCrtr(String applicationName){
       return (r, q, cb) -> cb.equal(r.get("applicationName"), applicationName);
    }

    public static Specification<AdmApplication> nameLikeCrtr(String applicationName){
        return (r, q, cb) -> cb.like(cb.lower(r.get("applicationName")), MessageFormat.format("%{0}%", applicationName.toLowerCase()));
    }

    public static Specification<AdmApplication> idCrtr(long applicationId){
        return (r, q, cb) -> cb.equal(r.get("applicationId"), applicationId);
    }
}
