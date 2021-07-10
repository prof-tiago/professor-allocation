package com.project.professor.allocation.controller;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.mapper.AllocationMapper;
import com.project.professor.allocation.service.AllocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/allocations")
public class AllocationController {

    private final AllocationService allocationService;
    private final AllocationMapper mapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Allocation>> findAll() {
        List<Allocation> allocations = allocationService.findAll();
        return new ResponseEntity<>(allocations, HttpStatus.OK);
    }

    @GetMapping(path = "/{allocation_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Allocation> findById(@PathVariable(name = "allocation_id") Long id) {
        Allocation allocation = allocationService.findById(id);
        return new ResponseEntity<>(allocation, HttpStatus.OK);
    }

    @GetMapping(path = "/professor/{professor_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Allocation>> findByProfessor(@PathVariable(name = "professor_id") Long id) {
        List<Allocation> allocations = allocationService.findByProfessor(id);
        return new ResponseEntity<>(allocations, HttpStatus.OK);
    }

    @GetMapping(path = "/course/{course_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Allocation>> findByCourse(@PathVariable(name = "course_id") Long id) {
        List<Allocation> allocations = allocationService.findByCourse(id);
        return new ResponseEntity<>(allocations, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Allocation> save(@RequestBody Allocation allocationBody) {
        Allocation allocation = allocationService.save(allocationBody);
        return new ResponseEntity<>(allocation, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{allocation_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Allocation> update(@PathVariable(name = "allocation_id") Long id,
                                             @RequestBody Allocation allocationBody) {
        allocationBody.setId(id);
        Allocation allocation = allocationService.update(allocationBody);
        return new ResponseEntity<>(allocation, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{allocation_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteById(@PathVariable(name = "allocation_id") Long id) {
        allocationService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteAll() {
        allocationService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
