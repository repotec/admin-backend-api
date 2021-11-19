package com.ntg.adm.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ntg.adm.model.AdmApplicationsTl;

public interface ApplicationTlRepository extends CrudRepository<AdmApplicationsTl, Long> {
	public List<AdmApplicationsTl> findByApplicationId(BigDecimal applicationId);
	public List<AdmApplicationsTl> findByApplicationIdAndLanguageCode(BigDecimal applicationId, String languageCode);
}
