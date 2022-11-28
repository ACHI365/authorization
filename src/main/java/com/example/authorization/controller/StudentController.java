package com.example.authorization.controller;

import com.example.authorization.models.Student;
import com.example.authorization.service.StudentService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api")
@PreAuthorize("hasRole('ROLE_STUDENT')")
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getStudents();
    }

    @GetMapping(path = "{studentId}")
    public Student getById(@PathVariable("studentId") long id) {
        return studentService.getStudentById(id);
    }

//    @PostMapping
//    public Student postStudent(@RequestBody Student student){
//        return studentService.createStudent(student);
//    }
//
//    @PutMapping(path = "{studentId}")
//    public void updateStudent(@PathVariable("studentId") long id,
//                              @RequestParam(required = false) String name) {
//        studentService.updateStudent(id, name);
//    }
//
//    @DeleteMapping(path = "{studentId}")
//    public void deleteStudent(@PathVariable long studentId){
//        studentService.deleteStudent(studentId);
//    }
}
