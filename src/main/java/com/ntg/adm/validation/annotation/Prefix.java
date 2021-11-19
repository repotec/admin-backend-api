package com.ntg.adm.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.ntg.adm.validation.PrefixValidator;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PrefixValidator.class)
public @interface Prefix {
	String value() default "app";
	
	String message() default "Please enter a valid prefix value."; 
	Class<?>[] groups() default {}; 
	Class<? extends Payload>[] payload() default {};
}
