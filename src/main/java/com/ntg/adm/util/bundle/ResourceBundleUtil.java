package com.ntg.adm.util.bundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
public class ResourceBundleUtil {
	
	@Autowired
	@Qualifier("messages")
	private ReloadableResourceBundleMessageSource messageSource;
	
//	public ResourceBundleUtil(@Qualifier("messages") ReloadableResourceBundleMessageSource messageSource) {
//		ResourceBundleUtil.messageSource = messageSource;
//	}
//	
	public String getMessage(String key) {
		return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
	}
	
	public String getMessage(String key, Object...parameters) {
		return messageSource.getMessage(key, parameters, LocaleContextHolder.getLocale());
	}
}
