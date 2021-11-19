package com.ntg.adm.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntg.adm.dao.RoleRepository;
import com.ntg.adm.model.AdmRole;

@Service
public class RoleService {
	@Autowired
	RoleRepository roleRepository;
	
	public Iterable<AdmRole> findAll(){
		return roleRepository.findAll();
	}
	
	public Optional<AdmRole> findById(long roleId){
		return roleRepository.findById(roleId);
	}
	
	public AdmRole create(AdmRole role){
		return roleRepository.save(role);
	}

	public AdmRole update(AdmRole role) {
		return roleRepository.save(role);
	}
	
	public AdmRole delete(AdmRole role) {
		roleRepository.delete(role);
		return role; 
	}
}
