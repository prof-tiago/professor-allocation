package com.project.professor.allocation.dto;

import java.util.List;

public class DepartamentDTO extends DepartamentBaseDTO {

	private List<ProfessorBaseDTO> professors;

	public DepartamentDTO() {
		super();
	}

	public List<ProfessorBaseDTO> getProfessors() {
		return professors;
	}

	public void setProfessors(List<ProfessorBaseDTO> professors) {
		this.professors = professors;
	}
}
