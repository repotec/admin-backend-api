package com.ntg.adm.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AdminConfig {

	@Bean
	public AuditorAware<Long> auditorAware(){
		return new AuditorAwareConfig();
	}
}
