package com.worktwo.HW2.Annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordValidation,String> {

    @Override
    public void initialize(PasswordValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        boolean Upper = false,Lower = false,Size=false,Special=false,NotSpace=false;
        if(s.length()>=10){
            Size=true;
        }
        else{
            return false;
        }
        for(char c : s.toCharArray()){
            if(Character.isUpperCase(c)){
                Upper=true;
            }
            else if(Character.isLowerCase(c)){
                Lower=true;
            }
            else if(Character.isDigit(c)){
                Special=true;
            }
            else if (c == '!'||c=='@'||c=='#'||c=='$'||c=='%'||c=='^'||c=='&'||c=='*'||c=='+'||c=='-'||c=='~'){
                Special=true;
            }
            else if(c ==' '){
                NotSpace=true;
            }

        }
        if(Upper&&Lower&&Special&&!NotSpace){
            return true;
        }
        return false;

    }


}
