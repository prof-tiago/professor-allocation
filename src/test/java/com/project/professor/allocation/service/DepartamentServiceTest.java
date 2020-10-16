package com.project.professor.allocation.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.model.Departament;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class DepartamentServiceTest {

	@Autowired
	DepartamentService departamentService;

	@Test
	public void findAll() {
		// Arrange
		String name = null;

		// Act
		List<Departament> departaments = departamentService.findAll(name);

		// Print
		departaments.forEach(System.out::println);
	}

	@Test
	public void findById() {
		// Arrange
		Long id = 1L;

		// Act
		Departament departament = departamentService.findById(id);

		// Print
		System.out.println(departament);
	}

	@Test
	public void save() {
		// Arrange
		Departament departament = new Departament();
		departament.setId(null);
		departament.setName("Departament 1");

		// Act
		departament = departamentService.save(departament);

		// Print
		System.out.println(departament);
	}

	@Test
	public void update() {
		// Arrange
		Departament departament = new Departament();
		departament.setId(1L);
		departament.setName("Departament 2");

		// Act
		departament = departamentService.update(departament);

		// Print
		System.out.println(departament);
	}

	@Test
	public void deleteById() {
		// Arrange
		Long id = 1L;

		// Act
		departamentService.deleteById(id);
	}

	@Test
	public void deleteAll() {
		// Act
		departamentService.deleteAll();
	}
}
