package com.example.authorization.controller;

import com.example.authorization.models.Course;
import com.example.authorization.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Objects;

@Controller
@AllArgsConstructor
@ApiIgnore
@RequestMapping(path = "/")
public class CourseController {

    private CourseService courseService;

    @GetMapping("/courses")
    public String getHomeView(Model model) {
        return findPaginated(1, "courseName", "asc", model);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/add")
    public String getNewCourseView(Model model) {
        model.addAttribute("course", new Course());
        return "new_course";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/save")
    public String saveCourse(@ModelAttribute("course") Course course) {
        System.out.println(course.getCourseName());
        courseService.saveCourse(course);
        return "redirect:/courses";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/update/{id}")
    public String getUpdateView(@PathVariable( value = "id") long id, Model model) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("course", course);
        return "update_course";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable (value = "id") long id) {

        this.courseService.deleteCourseById(id);
        return "redirect:/courses";
    }


    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Course> page = courseService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Course> listCourses = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listCourses", listCourses);
        return "courses";
    }
}