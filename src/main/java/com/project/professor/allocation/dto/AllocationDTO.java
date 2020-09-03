package com.project.professor.allocation.dto;

public class AllocationDTO extends AllocationBaseDTO {

	private ProfessorBaseDTO professor;
	private CourseBaseDTO course;

	public AllocationDTO() {
		super();
	}

	public ProfessorBaseDTO getProfessor() {
		return professor;
	}

	public void setProfessor(ProfessorBaseDTO professor) {
		this.professor = professor;
	}

	public CourseBaseDTO getCourse() {
		return course;
	}

	public void setCourse(CourseBaseDTO course) {
		this.course = course;
	}
}
