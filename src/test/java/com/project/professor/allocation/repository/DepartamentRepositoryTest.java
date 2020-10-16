package com.project.professor.allocation.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.model.Departament;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class DepartamentRepositoryTest {

	@Autowired
	private DepartamentRepository departamentRepository;

	@Test
	public void findAll() {
		// Act
		List<Departament> departaments = departamentRepository.findAll();

		// Print
		departaments.stream().forEach(System.out::println);
	}

	@Test
	public void findById() {
		// Arrange
		Long id = 1L;

		// Act
		Departament departament = departamentRepository.findById(id).orElse(null);

		// Print
		System.out.println(departament);
	}

	@Test
	public void findByNameContainingIgnoreCase() {
		// Arrange
		String name = "Departament";

		// Act
		List<Departament> departaments = departamentRepository.findByNameContainingIgnoreCase(name);

		// Print
		departaments.stream().forEach(System.out::println);
	}

	@Test
	public void save_create() {
		// Arrange
		Departament departament = new Departament();
		departament.setId(null);
		departament.setName("Departament 1");

		// Act
		departament = departamentRepository.save(departament);

		// Print
		System.out.println(departament);
	}

	@Test
	public void save_update() {
		// Arrange
		Departament departament = new Departament();
		departament.setId(1L);
		departament.setName("Departament 2");

		// Act
		departament = departamentRepository.save(departament);

		// Print
		System.out.println(departament);
	}

	@Test
	public void deleteById() {
		// Arrange
		Long id = 1L;

		// Act
		departamentRepository.deleteById(id);
	}

	@Test
	public void deleteAll() {
		// Act
		departamentRepository.deleteAllInBatch();
	}
}
