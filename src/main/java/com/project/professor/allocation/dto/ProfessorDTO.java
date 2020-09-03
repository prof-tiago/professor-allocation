package com.project.professor.allocation.dto;

import java.util.List;

public class ProfessorDTO extends ProfessorBaseDTO {

	private List<AllocationOnlyWithCourseDTO> allocations;

	public ProfessorDTO() {
		super();
	}

	public List<AllocationOnlyWithCourseDTO> getAllocations() {
		return allocations;
	}

	public void setAllocations(List<AllocationOnlyWithCourseDTO> allocations) {
		this.allocations = allocations;
	}
}
