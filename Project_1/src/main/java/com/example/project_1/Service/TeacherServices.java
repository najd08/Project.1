package com.example.project_1.Service;

import com.example.project_1.Model.Teacher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TeacherServices {
    ArrayList<Teacher> teachers= new ArrayList<>();

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public void addTeachers(Teacher teacher) {
        teachers.add(teacher);
    }

    public boolean updateTeachers(Integer id, Teacher teacher) {
        for (int i=0 ; i<teachers.size() ; i++){
            if(teachers.get(i).getID()==id){
                teachers.set(id,teacher);
                return true;
            }
        }
        return false;
    }

    public boolean deleteTeachers(Integer id) {
        for (int i=0 ; i<teachers.size() ; i++){
            if(teachers.get(i).getID()==id){
                teachers.remove(id);
                return true;
            }
        }
        return false;
    }

    public String idTeachers(Integer id) {
        for (int i=0 ; i< teachers.size();i++) {
            if (id.equals(teachers.get(i).getID()))
                return teachers.get(i).getName();
        }
        return null;
    }

    public ArrayList<String> salaryTeachers(double salary) {
        ArrayList<String> arr= new ArrayList<>();
        boolean cou=false;
        for (int i=0; i<teachers.size(); i++){
            if (salary<=teachers.get(i).getSalary()){
                arr.add(i,teachers.get(i).getName());
                cou=true;
            }
        }
        if(cou==true){
            return arr;}
        return null;
    }
}
