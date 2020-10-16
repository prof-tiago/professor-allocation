package com.project.professor.allocation.repository;

import java.sql.Time;
import java.time.DayOfWeek;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.model.Allocation;
import com.project.professor.allocation.model.Course;
import com.project.professor.allocation.model.Professor;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class AllocationRepositoryTest {

	@Autowired
	private AllocationRepository allocationRepository;

	@Test
	public void findAll() {
		// Act
		List<Allocation> allocations = allocationRepository.findAll();

		// Print
		allocations.stream().forEach(System.out::println);
	}

	@Test
	public void findById() {
		// Arrange
		Long id = 1L;

		// Act
		Allocation allocation = allocationRepository.findById(id).orElse(null);

		// Print
		System.out.println(allocation);
	}

	@Test
	public void findByProfessor() {
		// Arrange
		Professor professor = new Professor();
		professor.setId(1L);

		// Act
		List<Allocation> allocations = allocationRepository.findByProfessor(professor);

		// Print
		allocations.stream().forEach(System.out::println);
	}

	@Test
	public void findByCourse() {
		// Arrange
		Course course = new Course();
		course.setId(1L);

		// Act
		List<Allocation> allocations = allocationRepository.findByCourse(course);

		// Print
		allocations.stream().forEach(System.out::println);
	}

	@Test
	public void save_create() {
		// Arrange
		Professor professor = new Professor();
		professor.setId(1L);

		Course course = new Course();
		course.setId(1L);

		Allocation allocation = new Allocation();
		allocation.setId(null);
		allocation.setDayOfWeek(DayOfWeek.SUNDAY);
		allocation.setStartHour(Time.valueOf("17:00:00"));
		allocation.setEndHour(Time.valueOf("18:00:00"));
		allocation.setProfessor(professor);
		allocation.setCourse(course);

		// Act
		allocation = allocationRepository.save(allocation);

		// Print
		System.out.println(allocation);
	}

	@Test
	public void save_update() {
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
		allocation = allocationRepository.save(allocation);

		// Print
		System.out.println(allocation);
	}

	@Test
	public void deleteById() {
		// Arrange
		Long id = 1L;

		// Act
		allocationRepository.deleteById(id);
	}

	@Test
	public void deleteAll() {
		// Act
		allocationRepository.deleteAllInBatch();
	}
}
