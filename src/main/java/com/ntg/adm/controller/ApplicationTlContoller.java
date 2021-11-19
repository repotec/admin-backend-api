package com.ntg.adm.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ntg.adm.model.AdmApplicationsTl;
import com.ntg.adm.service.ApplicationTlService;

@RestController
@RequestMapping("/applications")
public class ApplicationTlContoller {

	@Autowired
	ApplicationTlService applicationTlService;
	
	@RequestMapping(value= "/transaction/id", method = RequestMethod.GET)
	public List<AdmApplicationsTl> findByApplicationId(@RequestParam BigDecimal applicationId){
		return applicationTlService.findByApplicationId(applicationId);
	}

	@RequestMapping(value= "/transaction/id/{langCode}", method = RequestMethod.GET)
	public List<AdmApplicationsTl> findByApplicationIdAndLanguageCode(@RequestParam BigDecimal applicationId, @PathVariable String langCode){
		return applicationTlService.findByApplicationIdAndLanguageCode(applicationId, langCode);
	}

}
