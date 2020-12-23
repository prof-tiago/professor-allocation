package com.project.professor.allocation.controller;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.mapper.DepartmentMapper;
import com.project.professor.allocation.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"departments"})
@RestController
@RequestMapping(path = "/departments", produces = MediaType.APPLICATION_JSON_VALUE)
public class DepartmentController {

    private final DepartmentService departmentService;
    private final DepartmentMapper mapper;

    public DepartmentController(DepartmentService departmentService, DepartmentMapper mapper) {
        super();
        this.departmentService = departmentService;
        this.mapper = mapper;
    }

    @ApiOperation(value = "Find all departments")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK")
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Department>> findAll(@RequestParam(name = "name", required = false) String name) {
        List<Department> departments = departmentService.findAll(name);
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @ApiOperation(value = "Find a department by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @GetMapping(path = "/{department_id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Department> findById(@PathVariable(name = "department_id") Long id) {
        Department department = departmentService.findById(id);
        if (department == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(department, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Save a department")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Department> save(@RequestBody Department department) {
        department = departmentService.save(department);
        if (department == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(department, HttpStatus.CREATED);
        }
    }

    @ApiOperation(value = "Update a department")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @PutMapping(path = "/{department_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Department> update(@PathVariable(name = "department_id") Long id,
                                             @RequestBody Department department) {
        department.setId(id);
        department = departmentService.update(department);
        if (department == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(department, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Delete a department")
    @ApiResponses({
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @DeleteMapping(path = "/{department_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteById(@PathVariable(name = "department_id") Long id) {
        departmentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Delete all departments")
    @ApiResponses({
            @ApiResponse(code = 204, message = "No Content")
    })
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteAll() {
        departmentService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
