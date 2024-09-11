package com.abhijit.SpringSecurity.controller;

import com.abhijit.SpringSecurity.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RestController
public class StudentController {

   private List<Student>students=new ArrayList<>(List.of(
          new Student(1,"abhi",12),
           new Student(2,"lokesh",13)


           ));


@GetMapping("/students")
    public List<Student> getStudents(){
    return students;
}

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student) {
        students.add(student);
        return student;
    }
}
