package com.ntg.adm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntg.adm.dao.UserRoleRepository;
import com.ntg.adm.model.AdmUserRole;

@Service
public class UserRoleService {
	@Autowired
	UserRoleRepository userRoleRepository;
	
	public Iterable<AdmUserRole> findAll(){
		return userRoleRepository.findAll();
	}
}
