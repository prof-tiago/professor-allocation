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
import com.project.professor.allocation.model.Professor;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class ProfessorRepositoryTest {

	@Autowired
	private ProfessorRepository professorRepository;

	@Test
	public void findAll() {
		// Act
		List<Professor> professors = professorRepository.findAll();

		// Print
		professors.stream().forEach(System.out::println);
	}

	@Test
	public void findById() {
		// Arrange
		Long id = 1L;

		// Act
		Professor professor = professorRepository.findById(id).orElse(null);

		// Print
		System.out.println(professor);
	}

	@Test
	public void findByNameContainingIgnoreCase() {
		// Arrange
		String name = "Professor";

		// Act
		List<Professor> professors = professorRepository.findByNameContainingIgnoreCase(name);

		// Print
		professors.stream().forEach(System.out::println);
	}

	@Test
	public void save_create() {
		// Arrange
		Departament departament = new Departament();
		departament.setId(1L);

		Professor professor = new Professor();
		professor.setId(null);
		professor.setName("Professor 1");
		professor.setCpf("111.111.111-11");
		professor.setDepartament(departament);

		// Act
		professor = professorRepository.save(professor);

		// Print
		System.out.println(professor);
	}

	@Test
	public void save_update() {
		// Arrange
		Departament departament = new Departament();
		departament.setId(1L);

		Professor professor = new Professor();
		professor.setId(1L);
		professor.setName("Professor 2");
		professor.setCpf("222.222.222-22");
		professor.setDepartament(departament);

		// Act
		professor = professorRepository.save(professor);

		// Print
		System.out.println(professor);
	}

	@Test
	public void deleteById() {
		// Arrange
		Long id = 1L;

		// Act
		professorRepository.deleteById(id);
	}

	@Test
	public void deleteAll() {
		// Act
		professorRepository.deleteAllInBatch();
	}
}
