package com.ntg.adm.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.ntg.adm.validation.ApplicationNamesValidator;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = ApplicationNamesValidator.class)
public @interface ApplicationNamesChecker {
	String firstFieldName();
	String dependFieldName();
	
	String message() default "Please enter same value in 'Default Application Name' without prefix."; 
	Class<?>[] groups() default {}; 
	Class<? extends Payload>[] payload() default {};
}
