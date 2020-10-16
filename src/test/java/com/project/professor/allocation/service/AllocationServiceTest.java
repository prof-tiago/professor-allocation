package com.project.professor.allocation.service;

import java.sql.Time;
import java.time.DayOfWeek;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.model.Allocation;
import com.project.professor.allocation.model.Course;
import com.project.professor.allocation.model.Professor;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class AllocationServiceTest {

	@Autowired
	AllocationService allocationService;

	@Test
	public void findAll() {
		// Act
		List<Allocation> allocations = allocationService.findAll();

		// Print
		allocations.forEach(System.out::println);
	}

	@Test
	public void findByProfessor() {
		// Arrange
		Long id = 1L;

		// Act
		List<Allocation> allocations = allocationService.findByProfessor(id);

		// Print
		allocations.forEach(System.out::println);
	}

	@Test
	public void findByCourse() {
		// Arrange
		Long id = 1L;

		// Act
		List<Allocation> allocations = allocationService.findByCourse(id);

		// Print
		allocations.forEach(System.out::println);
	}

	@Test
	public void findById() {
		// Arrange
		Long id = 1L;

		// Act
		Allocation allocation = allocationService.findById(id);

		// Print
		System.out.println(allocation);
	}

	@Test
	public void save() {
		// Arrange
		Professor professor = new Professor();
		professor.setId(1L);

		Course course = new Course();
		course.setId(1L);

		Allocation allocation = new Allocation();
		allocation.setId(null);
		allocation.setDayOfWeek(DayOfWeek.WEDNESDAY);
		allocation.setStartHour(Time.valueOf("19:00:00"));
		allocation.setEndHour(Time.valueOf("20:00:00"));
		allocation.setProfessor(professor);
		allocation.setCourse(course);

		// Act
		allocation = allocationService.save(allocation);

		// Print
		System.out.println(allocation);
	}

	@Test
	public void update() {
		// Arrange
		Professor professor = new Professor();
		professor.setId(1L);

		Course course = new Course();
		course.setId(1L);

		Allocation allocation = new Allocation();
		allocation.setId(1L);
		allocation.setDayOfWeek(DayOfWeek.MONDAY);
		allocation.setStartHour(Time.valueOf("19:00:00"));
		allocation.setEndHour(Time.valueOf("20:00:00"));
		allocation.setProfessor(professor);
		allocation.setCourse(course);

		// Act
		allocation = allocationService.update(allocation);

		// Print
		System.out.println(allocation);
	}

	@Test
	public void deleteById() {
		// Arrange
		Long id = 1L;

		// Act
		allocationService.deleteById(id);
	}

	@Test
	public void deleteAll() {
		// Act
		allocationService.deleteAll();
	}
}
