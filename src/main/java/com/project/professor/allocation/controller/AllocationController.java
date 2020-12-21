package com.project.professor.allocation.controller;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.service.AllocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/allocations", produces = MediaType.APPLICATION_JSON_VALUE)
public class AllocationController {

    private final AllocationService allocationService;

    public AllocationController(AllocationService allocationService) {
        super();
        this.allocationService = allocationService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Allocation>> findAll() {
        List<Allocation> allocations = allocationService.findAll();
        return new ResponseEntity<>(allocations, HttpStatus.OK);
    }

    @GetMapping(path = "/{allocation_id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Allocation> findById(@PathVariable(name = "allocation_id") Long id) {
        Allocation allocation = allocationService.findById(id);
        if (allocation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(allocation, HttpStatus.OK);
        }
    }

    @GetMapping(path = "/professor/{professor_id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Allocation>> findByProfessor(@PathVariable(name = "professor_id") Long id) {
        List<Allocation> allocations = allocationService.findByProfessor(id);
        return new ResponseEntity<>(allocations, HttpStatus.OK);
    }

    @GetMapping(path = "/course/{course_id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Allocation>> findByCourse(@PathVariable(name = "course_id") Long id) {
        List<Allocation> allocations = allocationService.findByCourse(id);
        return new ResponseEntity<>(allocations, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Allocation> save(@RequestBody Allocation allocation) {
        allocation = allocationService.save(allocation);
        if (allocation == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(allocation, HttpStatus.CREATED);
        }
    }
}
