package com.example.project_1.Controller;

import com.example.project_1.ApiResponse;
import com.example.project_1.Model.Student;
import com.example.project_1.Service.StudentServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static java.util.Collections.emptyList;

@RestController @RequestMapping("/api/v1") @RequiredArgsConstructor
public class StudentController {
    public final StudentServices studentServices;
    @GetMapping("/Students")
    public ResponseEntity getStudents(){
        ArrayList<Student> students= studentServices.getStudent();
        return ResponseEntity.status(200).body(students);
    }
    @PostMapping("/add")
    public ResponseEntity addStudents(@RequestBody @Valid Student student, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        studentServices.addStudents(student);
        return ResponseEntity.status(200).body(new ApiResponse("Student has been added!"));
    }

    @PutMapping("/Update/{index}")
    public ResponseEntity updateStudents(@PathVariable Integer id,@RequestBody @Valid Student student, Errors errors,@PathVariable String index){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated= studentServices.updateStudents(id,student);
        if (isUpdated)
            return ResponseEntity.status(200).body(new ApiResponse("Info has been updated"));
        return ResponseEntity.status(400).body(new ApiResponse("Check your id again!"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudents(@PathVariable @Valid Integer id, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();;
            return ResponseEntity.status(400).body(message);
        }
        boolean isDeleted=studentServices.deleteStudents(id);
        if (isDeleted)
            return ResponseEntity.status(200).body(new ApiResponse("Deleted!"));
        return ResponseEntity.status(400).body(new ApiResponse("Wrong id!"));
    }
    @GetMapping("/id/Student/{ID}")
    public ResponseEntity byIdStudents(@PathVariable ("ID") Integer stuId, @Valid Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        ResponseEntity matchId=new ResponseEntity<>(studentServices.byIdStudents(stuId), HttpStatus.OK);
        return ResponseEntity.status(200).body(matchId);
    }
    @GetMapping ("/byName/{name}")
    public ResponseEntity byNameStudents(@PathVariable @RequestBody @Valid String name, Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        Integer student=studentServices.byNameStudents(name);
        if (student==0)
            return ResponseEntity.status(400).body(new ApiResponse("name doesn't exist"));
        return ResponseEntity.status(200).body(student);
    }
    @GetMapping("/byMajor/{major}")
    public ResponseEntity byMajorStudents(@PathVariable @RequestBody @Valid String major,Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if (studentServices.byMajorStudents(major)== emptyList())
            return ResponseEntity.status(400).body(new ApiResponse("no student found"));

        return ResponseEntity.status(200).body(studentServices.byMajorStudents(major));
    }
    @GetMapping("/AgeStudents/{age}")
    public ResponseEntity ageStudents(@PathVariable @RequestBody @Valid String age, Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        return ResponseEntity.status(200).body(studentServices.ageStudents(age));
    }

}

