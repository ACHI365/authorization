package com.example.authorization.service;

import com.example.authorization.models.Course;
import com.example.authorization.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public void saveCourse(Course course) {
        this.courseRepository.save(course);
    }

    public Course getCourseById(long id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        Course course = null;
        if (optionalCourse.isPresent()) {
            course = optionalCourse.get();
        } else {
            throw new RuntimeException("Course not found for id : " + id);
        }
        return course;
    }

    public void deleteCourseById(long id) {
        this.courseRepository.deleteById(id);
    }

    public Page<Course> findPaginated(int pageNum, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        return this.courseRepository.findAll(pageable);
    }
}