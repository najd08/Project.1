package com.example.project_1.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class Teacher {
    @NotNull
    private Integer ID;
    @NotEmpty
    private String Name;
    @NotNull
    private double Salary;
}
