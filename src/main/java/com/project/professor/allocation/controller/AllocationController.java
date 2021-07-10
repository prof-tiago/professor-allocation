package com.project.professor.allocation.controller;

import com.project.professor.allocation.dto.AllocationCompleteDTO;
import com.project.professor.allocation.dto.AllocationCreationDTO;
import com.project.professor.allocation.dto.AllocationSimpleDTO;
import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.mapper.AllocationMapper;
import com.project.professor.allocation.service.AllocationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = {"courses"})
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/allocations")
public class AllocationController {

    private final AllocationService allocationService;
    private final AllocationMapper mapper;

    @ApiOperation(value = "Find all allocations")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AllocationSimpleDTO>> findAll() {
        List<Allocation> allocations = allocationService.findAll();
        return new ResponseEntity<>(mapper.toSimpleDTO(allocations), HttpStatus.OK);
    }

    @ApiOperation(value = "Find an allocation by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @GetMapping(path = "/{allocation_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AllocationCompleteDTO> findById(@PathVariable(name = "allocation_id") Long id) {
        Allocation allocation = allocationService.findById(id);
        return new ResponseEntity<>(mapper.toCompleteDTO(allocation), HttpStatus.OK);
    }

    @ApiOperation(value = "Find allocations by professor")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @GetMapping(path = "/professor/{professor_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AllocationSimpleDTO>> findByProfessor(@PathVariable(name = "professor_id") Long id) {
        List<Allocation> allocations = allocationService.findByProfessor(id);
        return new ResponseEntity<>(mapper.toSimpleDTO(allocations), HttpStatus.OK);
    }

    @ApiOperation(value = "Find allocations by course")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @GetMapping(path = "/course/{course_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AllocationSimpleDTO>> findByCourse(@PathVariable(name = "course_id") Long id) {
        List<Allocation> allocations = allocationService.findByCourse(id);
        return new ResponseEntity<>(mapper.toSimpleDTO(allocations), HttpStatus.OK);
    }

    @ApiOperation(value = "Save an allocation")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AllocationSimpleDTO> save(@Valid @RequestBody AllocationCreationDTO allocationBody) {
        Allocation allocation = allocationService.save(mapper.toEntity(allocationBody));
        return new ResponseEntity<>(mapper.toSimpleDTO(allocation), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update an allocation")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @PutMapping(path = "/{allocation_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AllocationSimpleDTO> update(@PathVariable(name = "allocation_id") Long id,
                                                      @Valid @RequestBody AllocationCreationDTO allocationBody) {
        allocationBody.setId(id);
        Allocation allocation = allocationService.update(mapper.toEntity(allocationBody));
        return new ResponseEntity<>(mapper.toSimpleDTO(allocation), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete an allocation")
    @ApiResponses({
            @ApiResponse(code = 204, message = "No Content")
    })
    @DeleteMapping(path = "/{allocation_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteById(@PathVariable(name = "allocation_id") Long id) {
        allocationService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Delete all allocations")
    @ApiResponses({
            @ApiResponse(code = 204, message = "No Content")
    })
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteAll() {
        allocationService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
