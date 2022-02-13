package com.ntg.adm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntg.adm.dao.UserRepository;
import com.ntg.adm.dto.mapper.principle.UserPrincipleMapper;
import com.ntg.adm.dto.principle.UserPrincipleDTO;
import com.ntg.adm.model.AdmUser;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserPrincipleMapper userPrincipleMapper;
	
	public Iterable<AdmUser> findAll(){
		return userRepository.findAll();
	}
	
	public UserPrincipleDTO getUserByName(String username) {
		UserPrincipleDTO userPrinciple = userPrincipleMapper.UserToUserPrinciple(userRepository.getByUserName(username));
		return userPrinciple;
	}
}
