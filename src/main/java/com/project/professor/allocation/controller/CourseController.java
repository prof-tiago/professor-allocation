package com.project.professor.allocation.controller;

import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.mapper.CourseMapper;
import com.project.professor.allocation.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/courses")
public class CourseController {

    private final CourseService courseService;
    private final CourseMapper mapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Course>> findAll(@RequestParam(name = "name", required = false) String name) {
        List<Course> courses = courseService.findAll(name);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping(path = "/{course_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Course> findById(@PathVariable(name = "course_id") Long id) {
        Course course = courseService.findById(id);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Course> save(@RequestBody Course courseBody) {
        Course course = courseService.save(courseBody);
        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{course_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Course> update(@PathVariable(name = "course_id") Long id,
                                         @RequestBody Course courseBody) {
        courseBody.setId(id);
        Course course = courseService.update(courseBody);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{course_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteById(@PathVariable(name = "course_id") Long id) {
        courseService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteAll() {
        courseService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
