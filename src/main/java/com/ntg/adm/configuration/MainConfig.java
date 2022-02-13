package com.ntg.adm.configuration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableCaching
public class MainConfig {

	@Bean
	public AuditorAware<Long> auditorAware(){
		return new AuditorAwareConfig();
	}
}
