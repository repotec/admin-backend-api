package com.ntg.adm.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntg.adm.dao.ApplicationTlRepository;
import com.ntg.adm.model.AdmApplicationsTl;

@Service	
public class ApplicationTlService {
	
	@Autowired
	ApplicationTlRepository admApplicationTlRepository;
	
	public Iterable<AdmApplicationsTl> findAll(){
		return admApplicationTlRepository.findAll();
	}
	
	public List<AdmApplicationsTl> findByApplicationId(BigDecimal applicationId){
		return admApplicationTlRepository.findByApplicationId(applicationId);
	}
	
	public List<AdmApplicationsTl> findByApplicationIdAndLanguageCode(BigDecimal applicationId, String languageCode){
		return admApplicationTlRepository.findByApplicationIdAndLanguageCode(applicationId, languageCode);
	}
}
	