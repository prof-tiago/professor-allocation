package com.project.professor.allocation.dto;

public class AllocationOnlyWithProfessorDTO extends AllocationBaseDTO {

	private ProfessorBaseDTO professor;

	public AllocationOnlyWithProfessorDTO() {
		super();
	}

	public ProfessorBaseDTO getProfessor() {
		return professor;
	}

	public void setProfessor(ProfessorBaseDTO professor) {
		this.professor = professor;
	}
}
