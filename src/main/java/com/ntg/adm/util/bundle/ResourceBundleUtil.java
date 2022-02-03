package com.ntg.adm.util.bundle;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
public class ResourceBundleUtil {
	private static ResourceBundleMessageSource messageSource;
	
	
	public ResourceBundleUtil(@Qualifier("messages") ResourceBundleMessageSource messageSource) {
		ResourceBundleUtil.messageSource = messageSource;
	}
	
	public static String getMessage(String key) {
		Locale locale = LocaleContextHolder.getLocale();
		messageSource.getMessage(key, null, locale);
		
		return null;
	}
}
