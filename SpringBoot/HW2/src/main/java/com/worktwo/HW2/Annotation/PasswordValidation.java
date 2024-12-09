package com.worktwo.HW2.Annotation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.springframework.stereotype.Controller;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface PasswordValidation {
    String message() default "Entered password should have uppercase,lowercase,special character, minimumlength of 10 characters,cannot contain space in between. ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
