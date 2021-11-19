package com.ntg.adm.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ntg.adm.exception.RecordNotFoundException;
import com.ntg.adm.model.AdmFunction;
import com.ntg.adm.service.FunctionService;

@RestController
@RequestMapping("/functions")
public class FunctionController {

	@Autowired
	FunctionService functionService;
	
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<AdmFunction> findAll(){
		return functionService.findAll();
	}
	
	@RequestMapping(value = "/id", method = RequestMethod.GET)
	public Optional<AdmFunction> findById(@RequestParam(name="functionId") long functionId){
		return Optional.ofNullable(functionService.findById(functionId).orElseThrow(()-> new RecordNotFoundException("Function is not found.")));
	}
}
