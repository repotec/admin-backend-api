package com.ntg.adm.security;

import com.ntg.adm.dto.mapper.principle.UserPrincipleMapper;
import com.ntg.adm.dto.principle.UserPrincipleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.ntg.adm.dao.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserPrincipleMapper userPrincipleMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserPrincipleDTO userPrinciple = userPrincipleMapper.UserToUserPrinciple(userRepository.getByUserName(username));

		if(userPrinciple == null)
			throw new UsernameNotFoundException("user is not found");

		return new UserPrinciple(userPrinciple);
	}
}