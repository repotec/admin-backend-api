package com.ntg.adm.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.beanutils.BeanUtils;
import com.ntg.adm.validation.annotation.ApplicationNamesChecker;

public class ApplicationNamesValidator implements ConstraintValidator<ApplicationNamesChecker, Object>{
	
	String firstFieldName;
	String dependFieldName;
	
	@Override
	public void initialize(ApplicationNamesChecker constraintAnnotation) {
		firstFieldName = constraintAnnotation.firstFieldName();
		dependFieldName = constraintAnnotation.dependFieldName();
		ConstraintValidator.super.initialize(constraintAnnotation);
	}
	
	@Override
	public boolean isValid(final Object value, ConstraintValidatorContext context) {
		boolean valid = true;
		try {
			final Object fieldValue = BeanUtils.getProperty(value, firstFieldName);
			final Object dependFieldValue = BeanUtils.getProperty(value, dependFieldName);
			
			valid =  fieldValue == null && dependFieldValue == null || fieldValue != null && fieldValue.toString().replace("app", "").equals(dependFieldValue);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return valid;
	}
}
