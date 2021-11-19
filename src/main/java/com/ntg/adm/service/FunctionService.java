package com.ntg.adm.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntg.adm.dao.FunctionRepository;
import com.ntg.adm.model.AdmFunction;

@Service
public class FunctionService {

	@Autowired
	FunctionRepository functionRepository;
	
	public Iterable<AdmFunction> findAll(){
		return functionRepository.findAll();
	}

	public Optional<AdmFunction> findById(long functionId) {
		return functionRepository.findById(functionId);
	}
}
