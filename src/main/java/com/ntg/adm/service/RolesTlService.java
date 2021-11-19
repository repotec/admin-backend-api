package com.ntg.adm.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntg.adm.dao.RoleTlRepository;
import com.ntg.adm.model.AdmRolesTl;

@Service
public class RolesTlService {
	@Autowired
	RoleTlRepository roleTlRepository;
	
	public Iterable<AdmRolesTl> findAll(){
		return roleTlRepository.findAll();
	}
	
	public Optional<AdmRolesTl> findById(long applicationId){
		return roleTlRepository.findById(applicationId);
	}
	
	public AdmRolesTl create(AdmRolesTl application){
		return roleTlRepository.save(application);
	}

	public AdmRolesTl update(AdmRolesTl roleTl) {
		return roleTlRepository.save(roleTl);
	}
	
	public AdmRolesTl delete(AdmRolesTl roleTl) {
		roleTlRepository.delete(roleTl);
		return roleTl; 
	}
}
