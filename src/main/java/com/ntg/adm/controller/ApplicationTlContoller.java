package com.ntg.adm.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ntg.adm.model.AdmApplicationText;
import com.ntg.adm.service.ApplicationTlService;

@RestController
@RequestMapping("/application")
public class ApplicationTlContoller {

	@Autowired
	ApplicationTlService applicationTlService;
	
	@RequestMapping(value= "/text", method = RequestMethod.GET)
	public Iterable<AdmApplicationText> findAll(){
		return applicationTlService.findAll();
	}
	
	@RequestMapping(value= "/text/id", method = RequestMethod.GET)
	public List<AdmApplicationText> findByApplicationId(@RequestParam BigDecimal applicationId){
		return applicationTlService.findByApplicationId(applicationId);
	}

	@RequestMapping(value= "/text/id/{langCode}", method = RequestMethod.GET)
	public List<AdmApplicationText> findByApplicationIdAndLanguageCode(@RequestParam BigDecimal applicationId, @PathVariable String langCode){
		return applicationTlService.findByApplicationIdAndLanguageCode(applicationId, langCode);
	}

}
