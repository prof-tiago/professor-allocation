package com.project.professor.allocation.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.model.Course;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class CourseServiceTest {

	@Autowired
	CourseService courseService;

	@Test
	public void findAll() {
		// Arrange
		String name = null;

		// Act
		List<Course> courses = courseService.findAll(name);

		// Print
		courses.forEach(System.out::println);
	}

	@Test
	public void findById() {
		// Arrange
		Long id = 1L;

		// Act
		Course course = courseService.findById(id);

		// Print
		System.out.println(course);
	}

	@Test
	public void save() {
		// Arrange
		Course course = new Course();
		course.setId(null);
		course.setName("Course 1");

		// Act
		course = courseService.save(course);

		// Print
		System.out.println(course);
	}

	@Test
	public void update() {
		// Arrange
		Course course = new Course();
		course.setId(1L);
		course.setName("Course 2");

		// Act
		course = courseService.update(course);

		// Print
		System.out.println(course);
	}

	@Test
	public void deleteById() {
		// Arrange
		Long id = 1L;

		// Act
		courseService.deleteById(id);
	}

	@Test
	public void deleteAll() {
		// Act
		courseService.deleteAll();
	}
}
