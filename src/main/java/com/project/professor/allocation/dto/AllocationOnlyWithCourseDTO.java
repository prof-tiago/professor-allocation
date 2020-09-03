package com.project.professor.allocation.dto;

public class AllocationOnlyWithCourseDTO extends AllocationBaseDTO {

	private CourseBaseDTO course;

	public AllocationOnlyWithCourseDTO() {
		super();
	}

	public CourseBaseDTO getCourse() {
		return course;
	}

	public void setCourse(CourseBaseDTO course) {
		this.course = course;
	}
}
