package com.project.professor.allocation.controller;

import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/courses", produces = MediaType.APPLICATION_JSON_VALUE)
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        super();
        this.courseService = courseService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Course>> findAll(@RequestParam(name = "name", required = false) String name) {
        List<Course> courses = courseService.findAll(name);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping(path = "/{course_id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Course> findById(@PathVariable(name = "course_id") Long id) {
        Course course = courseService.findById(id);
        if (course == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(course, HttpStatus.OK);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Course> save(@RequestBody Course course) {
        course = courseService.save(course);
        if (course == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(course, HttpStatus.CREATED);
        }
    }
}
