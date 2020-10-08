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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.professor.allocation.controller.mapper.AllocationMapper;
import com.project.professor.allocation.dto.AllocationDTO;
import com.project.professor.allocation.model.Allocation;
import com.project.professor.allocation.service.AllocationService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/allocation", produces = MediaType.APPLICATION_JSON_VALUE)
public class AllocationController {

	private AllocationService allocationService;
	private AllocationMapper mapper;

	public AllocationController(AllocationService allocationService, AllocationMapper mapper) {
		super();
		this.allocationService = allocationService;
		this.mapper = mapper;
	}

	@ApiOperation(value = "Get all allocations")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK")
	})
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<AllocationDTO>> getAllocations() {
		List<Allocation> allocations = allocationService.findAll();
		return new ResponseEntity<>(mapper.toAllocationDTO(allocations), HttpStatus.OK);
	}

	@ApiOperation(value = "Get allocation by id")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 404, message = "Not Found")
	})
	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<AllocationDTO> getAllocation(@PathVariable(value = "id") Long id) {
		Allocation allocation = allocationService.findById(id);
		if (allocation == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(mapper.toAllocationDTO(allocation), HttpStatus.OK);
		}
	}

	@ApiOperation(value = "Get all allocations by professor")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 404, message = "Not Found")
	})
	@GetMapping(value = "/professor/{professor_id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<AllocationDTO>> getAllocationsByProfessor(@PathVariable(value = "professor_id") Long id) {
		List<Allocation> allocations = allocationService.findByProfessor(id);
		return new ResponseEntity<>(mapper.toAllocationDTO(allocations), HttpStatus.OK);
	}

	@ApiOperation(value = "Get all allocations by course")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 404, message = "Not Found")
	})
	@GetMapping(value = "/course/{course_id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<AllocationDTO>> getAllocationsByCourse(@PathVariable(value = "course_id") Long id) {
		List<Allocation> allocations = allocationService.findByCourse(id);
		return new ResponseEntity<>(mapper.toAllocationDTO(allocations), HttpStatus.OK);
	}

	@ApiOperation(value = "Create allocation")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Created"),
		@ApiResponse(code = 400, message = "Bad Request")
	})
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<AllocationDTO> createAllocation(@RequestBody AllocationDTO allocationDTO) {
		Allocation allocation = allocationService.save(mapper.toAllocation(allocationDTO));
		if (allocation == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(mapper.toAllocationDTO(allocation), HttpStatus.CREATED);
		}
	}

	@ApiOperation(value = "Update allocation")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 404, message = "Not Found")
	})
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<AllocationDTO> updateAllocation(@PathVariable(value = "id") Long id,
			@RequestBody AllocationDTO allocationDTO) {
		allocationDTO.setId(id);
		Allocation allocation = allocationService.update(mapper.toAllocation(allocationDTO));
		if (allocation == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(mapper.toAllocationDTO(allocation), HttpStatus.OK);
		}
	}

	@ApiOperation(value = "Delete allocation")
	@ApiResponses({
		@ApiResponse(code = 204, message = "No Content"),
		@ApiResponse(code = 400, message = "Bad Request")
	})
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteAllocation(@PathVariable(value = "id") Long id) {
		allocationService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "Delete all allocations")
	@ApiResponses({
		@ApiResponse(code = 204, message = "No Content")
	})
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteAllocations() {
		allocationService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
