package com.project.professor.allocation.controller;

import com.project.professor.allocation.dto.DepartmentCompleteDTO;
import com.project.professor.allocation.dto.DepartmentCreationDTO;
import com.project.professor.allocation.dto.DepartmentSimpleDTO;
import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.mapper.DepartmentMapper;
import com.project.professor.allocation.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {

    private final DepartmentService departmentService;
    private final DepartmentMapper mapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<DepartmentSimpleDTO>> findAll(@RequestParam(name = "name", required = false) String name) {
        List<Department> departments = departmentService.findAll(name);
        return new ResponseEntity<>(mapper.toSimpleDTO(departments), HttpStatus.OK);
    }

    @GetMapping(path = "/{department_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<DepartmentCompleteDTO> findById(@PathVariable(name = "department_id") Long id) {
        Department department = departmentService.findById(id);
        return new ResponseEntity<>(mapper.toCompleteDTO(department), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DepartmentSimpleDTO> save(@RequestBody DepartmentCreationDTO departmentBody) {
        Department department = departmentService.save(mapper.toEntity(departmentBody));
        return new ResponseEntity<>(mapper.toSimpleDTO(department), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{department_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<DepartmentSimpleDTO> update(@PathVariable(name = "department_id") Long id,
                                                      @RequestBody DepartmentCreationDTO departmentBody) {
        departmentBody.setId(id);
        Department department = departmentService.update(mapper.toEntity(departmentBody));
        return new ResponseEntity<>(mapper.toSimpleDTO(department), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{department_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteById(@PathVariable(name = "department_id") Long id) {
        departmentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteAll() {
        departmentService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
