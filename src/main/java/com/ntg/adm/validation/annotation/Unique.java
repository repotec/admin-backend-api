package com.ntg.adm.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.ntg.adm.base.FieldValueExists;
import com.ntg.adm.validation.UniqueValidator;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueValidator.class)
@Documented
public @interface Unique {
	String message() default "{unique.value.violation}";

    
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    Class<? extends FieldValueExists> service();
}
