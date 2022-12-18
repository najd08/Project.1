package com.example.project_1.Controller;

import com.example.project_1.ApiResponse;
import com.example.project_1.Model.Teacher;
import com.example.project_1.Service.TeacherServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController @RequestMapping("/api/v1") @RequiredArgsConstructor
public class TeacherController {
    TeacherServices teacherServices;
    public TeacherController(TeacherServices teacherServices){
        this.teacherServices=teacherServices;
    }
    @GetMapping("/Teachers")
    public ResponseEntity getTeachers(){
        ArrayList<Teacher> teacher= teacherServices.getTeachers();
        return ResponseEntity.status(200).body(teacher);
    }
    @PostMapping("/addTeacher")
    public ResponseEntity addTeachers(@RequestBody @Valid Teacher teacher, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        teacherServices.addTeachers(teacher);
        return ResponseEntity.status(200).body(new ApiResponse("Teacher has been added!"));
    }
    @PutMapping("/updateTeacher/{index}")
    public ResponseEntity updateTeachers(@PathVariable Integer id,@RequestBody @Valid Teacher teacher, Errors errors,@PathVariable String index){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated=teacherServices.updateTeachers(id,teacher);
        if(isUpdated)
            return ResponseEntity.status(200).body(new ApiResponse("Updated!"));
        return ResponseEntity.status(400).body(new ApiResponse("Wrong id"));
    }
    @DeleteMapping("/deleteTeacher/{id}")
    public ResponseEntity deleteTeachers(@PathVariable @Valid Integer id, Errors errors){
        if (errors.hasErrors()){
            String message= errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isDeleted=teacherServices.deleteTeachers(id);
        if (isDeleted)
            return ResponseEntity.status(200).body(new ApiResponse("Deleted!"));
        return ResponseEntity.status(400).body(new ApiResponse("Incorrect id!"));
    }
    @GetMapping("/id/Teachers/{id}")
    public ResponseEntity idTeachers(@PathVariable @Valid Integer id, Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        String teacher=teacherServices.idTeachers(id);
        if (teacher==null)
            return ResponseEntity.status(400).body(new ApiResponse("no id found for this T!"));
        return ResponseEntity.status(200).body(teacher);
    }
    @GetMapping("/Salary/{salary}")
    public ResponseEntity salaryTeachers(@PathVariable @Valid double salary,Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if (teacherServices.salaryTeachers(salary)==null)
            return ResponseEntity.status(400).body(new ApiResponse("no salary match!"));

        return ResponseEntity.status(200).body(teacherServices.salaryTeachers(salary));
    }

}
