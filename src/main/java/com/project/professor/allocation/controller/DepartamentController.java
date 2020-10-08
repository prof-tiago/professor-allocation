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

import com.project.professor.allocation.controller.mapper.DepartamentMapper;
import com.project.professor.allocation.dto.DepartamentBaseDTO;
import com.project.professor.allocation.dto.DepartamentDTO;
import com.project.professor.allocation.model.Departament;
import com.project.professor.allocation.service.DepartamentService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/departament", produces = MediaType.APPLICATION_JSON_VALUE)
public class DepartamentController {

	private DepartamentService departamentService;
	private DepartamentMapper mapper;

	public DepartamentController(DepartamentService departamentService, DepartamentMapper mapper) {
		super();
		this.departamentService = departamentService;
		this.mapper = mapper;
	}

	@ApiOperation(value = "Get all departaments")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK")
	})
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<DepartamentBaseDTO>> getDepartaments(
			@RequestParam(name = "name", required = false) String name) {
		List<Departament> departaments = departamentService.findAll(name);
		return new ResponseEntity<>(mapper.toDepartamentBaseDTO(departaments), HttpStatus.OK);
	}

	@ApiOperation(value = "Get departament")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 404, message = "Not Found")
	})
	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<DepartamentDTO> getDepartament(@PathVariable(value = "id") Long id) {
		Departament departament = departamentService.findById(id);
		if (departament == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(mapper.toDepartamentDTO(departament), HttpStatus.OK);
		}
	}

	@ApiOperation(value = "Create departament")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Created"),
		@ApiResponse(code = 400, message = "Bad Request")
	})
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<DepartamentBaseDTO> createDepartament(@RequestBody DepartamentBaseDTO departamentBaseDTO) {
		Departament departament = departamentService.save(mapper.toDepartament(departamentBaseDTO));
		if (departament == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(mapper.toDepartamentBaseDTO(departament), HttpStatus.CREATED);
		}
	}

	@ApiOperation(value = "Update departament")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 404, message = "Not Found")
	})
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<DepartamentBaseDTO> updateDepartament(@PathVariable(value = "id") Long id,
			@RequestBody DepartamentBaseDTO departamentBaseDTO) {
		departamentBaseDTO.setId(id);
		Departament departament = departamentService.update(mapper.toDepartament(departamentBaseDTO));
		if (departament == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(mapper.toDepartamentBaseDTO(departament), HttpStatus.OK);
		}
	}

	@ApiOperation(value = "Delete departament")
	@ApiResponses({
		@ApiResponse(code = 204, message = "No Content"),
		@ApiResponse(code = 400, message = "Bad Request")
	})
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteDepartament(@PathVariable(value = "id") Long id) {
		departamentService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "Delete all departaments")
	@ApiResponses({
		@ApiResponse(code = 204, message = "No Content")
	})
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteDepartaments() {
		departamentService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
