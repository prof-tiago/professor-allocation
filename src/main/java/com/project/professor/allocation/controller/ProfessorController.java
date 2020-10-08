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

import com.project.professor.allocation.controller.mapper.ProfessorMapper;
import com.project.professor.allocation.dto.ProfessorBaseDTO;
import com.project.professor.allocation.dto.ProfessorDTO;
import com.project.professor.allocation.model.Professor;
import com.project.professor.allocation.service.ProfessorService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/professor", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfessorController {

	private ProfessorService professorService;
	private ProfessorMapper mapper;

	public ProfessorController(ProfessorService professorService, ProfessorMapper mapper) {
		super();
		this.professorService = professorService;
		this.mapper = mapper;
	}

	@ApiOperation(value = "Get all professors")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK")
	})
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<ProfessorBaseDTO>> getProfessors(@RequestParam(name = "name", required = false) String name) {
		List<Professor> professors = professorService.findAll(name);
		return new ResponseEntity<>(mapper.toProfessorBaseDTO(professors), HttpStatus.OK);
	}

	@ApiOperation(value = "Get professor")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 404, message = "Not Found")
	})
	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<ProfessorDTO> getProfessor(@PathVariable(value = "id") Long id) {
		Professor professor = professorService.findById(id);
		if (professor == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(mapper.toProfessorDTO(professor), HttpStatus.OK);
		}
	}

	@ApiOperation(value = "Create professor")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Created"),
		@ApiResponse(code = 400, message = "Bad Request")
	})
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ProfessorBaseDTO> createProfessor(@RequestBody ProfessorBaseDTO professorBaseDTO) {
		Professor professor = professorService.save(mapper.toProfessor(professorBaseDTO));
		if (professor == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(mapper.toProfessorBaseDTO(professor), HttpStatus.CREATED);
		}
	}

	@ApiOperation(value = "Update professor")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 404, message = "Not Found")
	})
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<ProfessorBaseDTO> updateProfessor(@PathVariable(value = "id") Long id,
			@RequestBody ProfessorBaseDTO professorBaseDTO) {
		professorBaseDTO.setId(id);
		Professor professor = professorService.update(mapper.toProfessor(professorBaseDTO));
		if (professor == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(mapper.toProfessorBaseDTO(professor), HttpStatus.OK);
		}
	}

	@ApiOperation(value = "Delete professor")
	@ApiResponses({
		@ApiResponse(code = 204, message = "No Content"),
		@ApiResponse(code = 400, message = "Bad Request")
	})
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteProfessor(@PathVariable(value = "id") Long id) {
		professorService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "Delete all professors")
	@ApiResponses({
		@ApiResponse(code = 204, message = "No Content")
	})
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteProfessors() {
		professorService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
