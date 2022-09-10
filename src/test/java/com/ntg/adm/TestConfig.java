package com.ntg.adm;

import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@TestConfiguration
public class TestConfig {
	@Spy
	ReloadableResourceBundleMessageSource messages = messageSource();
	
	@Value("${bundle.baseName}")
	private String baseName;
	
	@Value("${bundle.defaultLocale}")
	private String defaultLocale;
	
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
		resource.setBasenames("classpath:" + baseName);
		resource.setDefaultEncoding("UTF-8");
		resource.setUseCodeAsDefaultMessage(true);
		resource.setCacheSeconds(-1); //default
		return resource;
	}
}
