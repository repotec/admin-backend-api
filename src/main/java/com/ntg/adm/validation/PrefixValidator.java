package com.ntg.adm.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ntg.adm.annotation.Prefix;

public class PrefixValidator implements ConstraintValidator<Prefix, String> {

	@Override
	public void initialize(Prefix constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value.startsWith("app")) return true;
		return false;
	}
}
