package com.ntg.adm.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ntg.adm.dto.mapper.principle.UserPrincipleMapper;
import com.ntg.adm.dto.principle.UserPrincipleDTO;
import com.ntg.adm.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserService userService;

	@Autowired
	UserPrincipleMapper userPrincipleMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserPrincipleDTO userPrinciple = userService.getUserByName(username);

		if(userPrinciple == null)
			throw new UsernameNotFoundException("user is not found");

		return new UserPrinciple(userPrinciple);
	}
}