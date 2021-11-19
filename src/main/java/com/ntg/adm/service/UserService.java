package com.ntg.adm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntg.adm.dao.UserRepository;
import com.ntg.adm.model.AdmUser;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public Iterable<AdmUser> findAll(){
		return userRepository.findAll();
	}
}
