package com.ntg.adm.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@EnableWebSecurity
public class AdmSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	@Qualifier(value = "userDetailsServiceImpl")
	UserDetailsService userDetailsService;

	@Bean
	public AuthenticationProvider sqlAuthenticationProvider() {
		DaoAuthenticationProvider sqlAuthenticationProvider = new DaoAuthenticationProvider();
		sqlAuthenticationProvider.setUserDetailsService(userDetailsService);
		sqlAuthenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		return sqlAuthenticationProvider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/actuator**").permitAll()
			.anyRequest().authenticated()
			.and().csrf().disable()
			.httpBasic();
	}
}
