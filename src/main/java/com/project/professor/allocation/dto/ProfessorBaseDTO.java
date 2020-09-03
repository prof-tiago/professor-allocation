package com.project.professor.allocation.dto;

public class ProfessorBaseDTO {

	private Long id;
	private String name;
	private String cpf;
	private DepartamentBaseDTO departament;

	public ProfessorBaseDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public DepartamentBaseDTO getDepartament() {
		return departament;
	}

	public void setDepartament(DepartamentBaseDTO departament) {
		this.departament = departament;
	}
}
