package com.worktwo.HW2.Dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {

    @NotBlank(message = "Title cannot be empty.")
    private String title;

    @AssertTrue(message = "Department should be always active.")
    @NotBlank
    private Boolean isActive;

    @PastOrPresent
    private LocalDateTime createdAt;
}
