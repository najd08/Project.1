package com.example.project_1.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class Student {
    @NotNull
    private Integer ID;
    @NotEmpty
    private String Name;
    @NotEmpty
    private String Age;
    @NotEmpty
    @Pattern(regexp = "(CS|MATH|Engineer)")
    private String Major;

}
