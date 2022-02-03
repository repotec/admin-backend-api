package com.ntg.adm.configuration;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import com.ntg.adm.dao.UserRepository;
import com.ntg.adm.security.UserPrinciple;

public class AuditorAwareConfig implements AuditorAware<Long> {
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public Optional<Long> getCurrentAuditor() {
		return Optional.of(((UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId());
	}
}