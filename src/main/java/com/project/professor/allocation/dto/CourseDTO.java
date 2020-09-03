package com.project.professor.allocation.dto;

import java.util.List;

public class CourseDTO extends CourseBaseDTO {

	private List<AllocationOnlyWithProfessorDTO> allocations;

	public CourseDTO() {
		super();
	}

	public List<AllocationOnlyWithProfessorDTO> getAllocations() {
		return allocations;
	}

	public void setAllocations(List<AllocationOnlyWithProfessorDTO> allocations) {
		this.allocations = allocations;
	}
}
