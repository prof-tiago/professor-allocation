package com.project.professor.allocation.controller;

import com.project.professor.allocation.dto.CourseCompleteDTO;
import com.project.professor.allocation.dto.CourseCreationDTO;
import com.project.professor.allocation.dto.CourseSimpleDTO;
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.mapper.CourseMapper;
import com.project.professor.allocation.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/courses")
public class CourseController {

    private final CourseService courseService;
    private final CourseMapper mapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CourseSimpleDTO>> findAll(@RequestParam(name = "name", required = false) String name) {
        List<Course> courses = courseService.findAll(name);
        return new ResponseEntity<>(mapper.toSimpleDTO(courses), HttpStatus.OK);
    }

    @GetMapping(path = "/{course_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CourseCompleteDTO> findById(@PathVariable(name = "course_id") Long id) {
        Course course = courseService.findById(id);
        return new ResponseEntity<>(mapper.toCompleteDTO(course), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CourseSimpleDTO> save(@Valid @RequestBody CourseCreationDTO courseBody) {
        Course course = courseService.save(mapper.toEntity(courseBody));
        return new ResponseEntity<>(mapper.toSimpleDTO(course), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{course_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CourseSimpleDTO> update(@PathVariable(name = "course_id") Long id,
                                                  @Valid @RequestBody CourseCreationDTO courseBody) {
        courseBody.setId(id);
        Course course = courseService.update(mapper.toEntity(courseBody));
        return new ResponseEntity<>(mapper.toSimpleDTO(course), HttpStatus.OK);
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
