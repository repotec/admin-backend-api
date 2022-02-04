package com.ntg.adm.util.bundle;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
public class ResourceBundleUtil {
	private static ReloadableResourceBundleMessageSource messageSource;
	
	
	public ResourceBundleUtil(@Qualifier("messages") ReloadableResourceBundleMessageSource messageSource) {
		ResourceBundleUtil.messageSource = messageSource;
	}
	
	public static String getMessage(String key) {
		return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
	}
	
	public static String getMessage(String key, Object...parameters) {
		return messageSource.getMessage(key, parameters, LocaleContextHolder.getLocale());
	}
}
