package com.example.authorization.service;

import com.example.authorization.models.Student;
import com.example.authorization.repository.studentRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final studentRepository studentRepository;

    public StudentService(studentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public Student getStudentById(long id){
        Optional<Student> student = studentRepository.findById(id);

        if(student.isEmpty())
            throw new IllegalStateException("Student with this id does not exists");

        return student.get();
    }

    public Student createStudent(Student student){
        return studentRepository.save(student);
    }

    public void deleteStudent(long id){
        if(!studentRepository.existsById(id))
            throw new IllegalStateException("Student with this id does not exists");

        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(long id, String name){
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "student with this id does not exist"
        ));

        if(name != null && name.length() > 0
                && !student.getName().equals(name))
            student.setName(name);
    }
}
