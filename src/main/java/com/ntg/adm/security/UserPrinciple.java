package com.ntg.adm.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ntg.adm.dto.principle.UserPrincipleDTO;

public class UserPrinciple implements UserDetails {
	private static final long serialVersionUID = 1L;

	UserPrincipleDTO admUser;
	
	public UserPrinciple(UserPrincipleDTO admUser) {
		this.admUser = admUser;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return admUser.getUserRolesPrinciple().stream().map(role -> new SimpleGrantedAuthority(role.getRolePrinciple().getRoleName())).collect(Collectors.toList());
	}
	
	public Long getUserId() {
        return admUser.getUserId();
    }

	@Override
	public String getPassword() {
		return this.admUser.getPassword();
	}

	@Override
	public String getUsername() {
		return this.admUser.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
