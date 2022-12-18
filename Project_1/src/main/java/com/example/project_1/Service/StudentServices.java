package com.example.project_1.Service;

import com.example.project_1.Model.Student;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class StudentServices {
    ArrayList<Student> students= new ArrayList<>();

    public ArrayList<Student> getStudent() {
        return students;
    }

    public void addStudents(Student student) {
        students.add(student);
    }

    public boolean updateStudents(Integer id, Student student) {
        for(int i=0 ; i<students.size();i++){
            if(students.get(i).getID()==id){
                students.set(i,student);
                return true;}
        }
        return false;
    }

    public boolean deleteStudents(Integer id) {
        for (int i=0 ; i<students.size(); i++){
            if(students.get(i).getID()==id){
                students.remove(id);
                return true;
            }
        }
        return false;
    }

    public @NotEmpty String byIdStudents(Integer stuId) {
        for (int i=0 ; i< students.size();i++) {
            if (stuId.equals(students.get(i).getID()))
                    return students.get(i).getName();
        }
        return null;
    }

    public Integer byNameStudents(String name) {
        for (int i=0 ; i< students.size();i++) {
            if (name.equals(students.get(i).getName()))
                return students.get(i).getID();
        }
        return 0;
    }


    public List byMajorStudents(String major) {
        List list = Collections.synchronizedList(new ArrayList());
        for (int i=0 ; i<students.size();i++){
            if (major==students.get(i).getMajor()) {
                list.add(i,students.get(i).getName());
            }
        }
        return list;
    }

    public ArrayList<String> ageStudents(String age) {
        ArrayList<String> ArrayList= new ArrayList<>();
        for (int i=0 ; i<students.size();i++){
            if (age==students.get(i).getAge()) {
                ArrayList.add(students.get(i).getName());
            }
        }
        return ArrayList;
    }
}
