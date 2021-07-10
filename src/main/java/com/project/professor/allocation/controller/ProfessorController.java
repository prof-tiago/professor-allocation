package com.project.professor.allocation.controller;

import com.project.professor.allocation.dto.ProfessorCompleteDTO;
import com.project.professor.allocation.dto.ProfessorCreationDTO;
import com.project.professor.allocation.dto.ProfessorSimpleDTO;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.mapper.ProfessorMapper;
import com.project.professor.allocation.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/professors")
public class ProfessorController {

    private final ProfessorService professorService;
    private final ProfessorMapper mapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProfessorSimpleDTO>> findAll(@RequestParam(name = "name", required = false) String name) {
        List<Professor> professors = professorService.findAll(name);
        return new ResponseEntity<>(mapper.toSimpleDTO(professors), HttpStatus.OK);
    }

    @GetMapping(path = "/{professor_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProfessorCompleteDTO> findById(@PathVariable(name = "professor_id") Long id) {
        Professor professor = professorService.findById(id);
        return new ResponseEntity<>(mapper.toCompleteDTO(professor), HttpStatus.OK);
    }

    @GetMapping(path = "/department/{department_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProfessorSimpleDTO>> findByDepartment(@PathVariable(name = "department_id") Long id) {
        List<Professor> professors = professorService.findByDepartment(id);
        return new ResponseEntity<>(mapper.toSimpleDTO(professors), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProfessorSimpleDTO> save(@Valid @RequestBody ProfessorCreationDTO professorBody) {
        Professor professor = professorService.save(mapper.toEntity(professorBody));
        return new ResponseEntity<>(mapper.toSimpleDTO(professor), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{professor_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProfessorSimpleDTO> update(@PathVariable(name = "professor_id") Long id,
                                                     @Valid @RequestBody ProfessorCreationDTO professorBody) {
        professorBody.setId(id);
        Professor professor = professorService.update(mapper.toEntity(professorBody));
        return new ResponseEntity<>(mapper.toSimpleDTO(professor), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{professor_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteById(@PathVariable(name = "professor_id") Long id) {
        professorService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteAll() {
        professorService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
