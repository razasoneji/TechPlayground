package com.worktwo.HW2.Annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static java.lang.Math.sqrt;

public class PrimeValidator implements ConstraintValidator<PrimeValidation,Integer>  {
    @Override
    public void initialize(PrimeValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        if(integer==0 || integer==1 || integer == 2) {
            return true;
        }
        for(int i =2;i<sqrt(integer);i++){
            if(integer%i==0){
                return false;
            }
        }
        return true;
    }
}
