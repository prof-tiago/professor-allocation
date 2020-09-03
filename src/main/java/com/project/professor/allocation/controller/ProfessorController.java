package com.project.professor.allocation.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.professor.allocation.model.Professor;
import com.project.professor.allocation.service.ProfessorService;

@RestController
@RequestMapping(path = "/professor", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfessorController {

	private ProfessorService professorService;

	public ProfessorController(ProfessorService professorService) {
		super();
		this.professorService = professorService;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Professor>> getProfessors(@RequestParam(name = "name", required = false) String name) {
		List<Professor> professors = professorService.findAll(name);
		return new ResponseEntity<>(professors, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Professor> getProfessor(@PathVariable(value = "id") Long id) {
		Professor professor = professorService.findById(id);
		if (professor == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(professor, HttpStatus.OK);
		}
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Professor> createProfessor(@RequestBody Professor professor) {
		professor = professorService.save(professor);
		if (professor == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(professor, HttpStatus.CREATED);
		}
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Professor> updateProfessor(@PathVariable(value = "id") Long id,
			@RequestBody Professor professor) {
		professor.setId(id);
		professor = professorService.update(professor);
		if (professor == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(professor, HttpStatus.OK);
		}
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteProfessor(@PathVariable(value = "id") Long id) {
		professorService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteProfessors() {
		professorService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
