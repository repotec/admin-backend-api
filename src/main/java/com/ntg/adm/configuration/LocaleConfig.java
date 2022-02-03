package com.ntg.adm.configuration;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@Configuration
public class LocaleConfig {
//	@Value("${app.baseName}")
//	private String baseName;
//	
//	@Value("${app.defaultLocale}")
//	private String defaultLocale;
	
	@Bean(name = "messages")
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
		resource.setBasenames("messages");
		resource.setDefaultEncoding("UTF-8");
		resource.setUseCodeAsDefaultMessage(true);
		
		return resource;
	}
	
	@Bean
	public LocaleResolver localeResolver() {
		AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();
		resolver.setDefaultLocale(Locale.ENGLISH);
		return resolver;
	}
}
