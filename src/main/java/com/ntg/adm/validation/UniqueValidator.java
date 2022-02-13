package com.ntg.adm.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.ntg.adm.annotation.Unique;
import com.ntg.adm.base.FieldValueExists;

public class UniqueValidator implements ConstraintValidator<Unique, Object>{
	@Autowired
    private ApplicationContext applicationContext;
	
	private FieldValueExists service;
    
	@Override
	public void initialize(Unique unique) {
		Class<? extends FieldValueExists> clazz = unique.service();
		
		this.service = this.applicationContext.getBean(clazz);
		
		ConstraintValidator.super.initialize(unique);
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		return !this.service.fieldValueExists(value);
	}

}
