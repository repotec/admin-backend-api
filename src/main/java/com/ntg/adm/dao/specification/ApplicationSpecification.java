package com.ntg.adm.dao.specification;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

//import com.sun.javafx.binding.StringFormatter;
import com.ntg.adm.util.query.SearchFilter;
import com.ntg.adm.util.query.SpecificationUtil;
import org.springframework.data.jpa.domain.Specification;

import com.ntg.adm.model.AdmApplication;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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

    public static Specification<AdmApplication> applicationFullCriteria(Object applicationId, Object applicationName, Object applicationUrl){
        return (Root<AdmApplication> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList();
                List<SearchFilter> searchFilters = new ArrayList() {{ add(new SearchFilter("applicationId", "=", applicationId));
                                                                      add(new SearchFilter("applicationName", "LIKE", applicationName));
                                                                      add(new SearchFilter("applicationUrl", "LIKE", applicationUrl));
                                                                    }};
                for (SearchFilter searchFilter: searchFilters) {
                    SpecificationUtil.addPredicates(predicates, searchFilter, criteriaBuilder, root);
                }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
