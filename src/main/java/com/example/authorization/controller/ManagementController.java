package com.example.authorization.controller;

import com.example.authorization.models.Student;
import com.example.authorization.models.User;
import com.example.authorization.security.UserRole;
import com.example.authorization.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/management/api")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class ManagementController {
    private final StudentService studentService;
    private final RegistrationController registrationController;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getStudents();
    }

    @GetMapping(path = "{studentId}")
    public Student getById(@PathVariable("studentId") long id) {
        return studentService.getStudentById(id);
    }

    @PostMapping
    public Student postStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") long id,
                              @RequestParam(required = false) String name) {
        studentService.updateStudent(id, name);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable long studentId) {
        studentService.deleteStudent(studentId);
    }

}
