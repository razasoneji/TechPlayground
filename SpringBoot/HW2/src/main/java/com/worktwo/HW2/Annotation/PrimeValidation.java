package com.worktwo.HW2.Annotation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {PrimeValidator.class})
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
public @interface PrimeValidation {
    String message() default "Number Entered should be prime.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
