package com.project.professor.allocation.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.professor.allocation.controller.mapper.CourseMapper;
import com.project.professor.allocation.dto.CourseBaseDTO;
import com.project.professor.allocation.dto.CourseDTO;
import com.project.professor.allocation.model.Course;
import com.project.professor.allocation.service.CourseService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/course")
public class CourseController {

	private CourseService courseService;
	private CourseMapper mapper;

	public CourseController(CourseService courseService, CourseMapper mapper) {
		super();
		this.courseService = courseService;
		this.mapper = mapper;
	}

	@ApiOperation(value = "Get all courses")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK")
	})
	@GetMapping
	public ResponseEntity<List<CourseBaseDTO>> getCourses(@RequestParam Optional<String> name) {
		List<Course> courses = courseService.findAll(name.orElse(null));
		return new ResponseEntity<>(mapper.toCourseBaseDTO(courses), HttpStatus.OK);
	}

	@ApiOperation(value = "Get course")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 404, message = "Not Found")
	})
	@GetMapping(value = "/{id}")
	public ResponseEntity<CourseDTO> getCourse(@PathVariable(value = "id") Long id) {
		Course course = courseService.findById(id);
		if (course == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(mapper.toCourseDTO(course), HttpStatus.OK);
		}
	}

	@ApiOperation(value = "Create course")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Created"),
		@ApiResponse(code = 400, message = "Bad Request")
	})
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CourseBaseDTO> createCourse(@RequestBody CourseBaseDTO courseBaseDTO) {
		Course course = courseService.save(mapper.toCourse(courseBaseDTO));
		if (course == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(mapper.toCourseBaseDTO(course), HttpStatus.CREATED);
		}
	}

	@ApiOperation(value = "Update course")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 404, message = "Not Found")
	})
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CourseBaseDTO> updateCourse(@PathVariable(value = "id") Long id, @RequestBody CourseBaseDTO courseBaseDTO) {
		courseBaseDTO.setId(id);
		Course course = courseService.update(mapper.toCourse(courseBaseDTO));
		if (course == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(mapper.toCourseBaseDTO(course), HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "Delete course")
	@ApiResponses({
		@ApiResponse(code = 204, message = "No Content"),
		@ApiResponse(code = 400, message = "Bad Request")
	})
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteCourse(@PathVariable(value = "id") Long id) {
		courseService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "Delete all courses")
	@ApiResponses({
		@ApiResponse(code = 204, message = "No Content")
	})
	@DeleteMapping
	public ResponseEntity<Void> deleteCourses() {
		courseService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
