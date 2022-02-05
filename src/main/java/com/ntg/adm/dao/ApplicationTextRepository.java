package com.ntg.adm.dao;

import java.math.BigDecimal;
import java.util.List;

import com.ntg.adm.base.BaseRepository;
import com.ntg.adm.model.AdmApplicationText;

public interface ApplicationTextRepository extends BaseRepository<AdmApplicationText, Long> {
	public List<AdmApplicationText> findByApplicationId(BigDecimal applicationId);
	public List<AdmApplicationText> findByApplicationIdAndLanguageCode(BigDecimal applicationId, String languageCode);
}
