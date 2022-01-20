package com.ntg.adm.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntg.adm.dao.ApplicationTextRepository;
import com.ntg.adm.model.AdmApplicationText;

@Service	
public class ApplicationTlService {
	
	@Autowired
	ApplicationTextRepository admApplicationTlRepository;
	
	public Iterable<AdmApplicationText> findAll(){
		return admApplicationTlRepository.findAll();
	}
	
	public List<AdmApplicationText> findByApplicationId(BigDecimal applicationId){
		return admApplicationTlRepository.findByApplicationId(applicationId);
	}
	
	public List<AdmApplicationText> findByApplicationIdAndLanguageCode(BigDecimal applicationId, String languageCode){
		return admApplicationTlRepository.findByApplicationIdAndLanguageCode(applicationId, languageCode);
	}
}
	