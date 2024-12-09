package com.worktwo.HW2.Dto;


import com.worktwo.HW2.Annotation.PasswordValidation;
import com.worktwo.HW2.Annotation.PrimeValidation;
import jakarta.validation.constraints.*;

import java.util.Date;

public class EmployeeDto {

    @NotBlank(message = "Name Cannot be Blank.")
    @Size(min = 3, max = 25,message = "Name cannot be less than 3 and more than 25.")
    private String name;


    private Boolean isActive;

    @Positive
    @Min(value = 20,message = "Age cannot be smaller than 20.")
    @Max(value = 65,message = "Age cannot be greater than 65.")
    @NotBlank(message = "Age cannot be blank.")
    private Integer age;

    @Positive
    @DecimalMax(value = "999999999.9999" ,message ="Cannot be more than 99999999.9999" )
    @DecimalMin(value = "111111.111",message = "Enter greater than 111111.11")
    @NotBlank
    private Double salary;

    @Positive
    private Integer workHours;

    @PastOrPresent
    @NotBlank
    private Date joiningDate;

    @Email
    @NotBlank
    private String Email;

    @PrimeValidation
    @NotBlank
    private Integer primeNumber;

    @PasswordValidation
    private String password;
}
