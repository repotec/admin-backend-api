package com.ntg.adm.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class CustomAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	@Qualifier("userDetailsServiceImpl")
	private UserDetailsService userDetailsService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String providedUsername = authentication.getPrincipal().toString();
		UserDetails user = userDetailsService.loadUserByUsername(providedUsername);
		System.out.println("User Details from UserService based on username-" + providedUsername + " : " + user);

		String providedPassword = authentication.getCredentials().toString();
		String correctPassword = user.getPassword();

		System.out.println("Provided Password - " + providedPassword + " Correct Password: " + correctPassword);

		if (!providedPassword.equals(correctPassword))
			throw new RuntimeException("Incorrect Credentials");

		Authentication authenticationResult = new UsernamePasswordAuthenticationToken(user,
				authentication.getCredentials(), user.getAuthorities());
		return authenticationResult;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}