package com.project.professor.allocation.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.project.professor.allocation.dto.CourseBaseDTO;
import com.project.professor.allocation.dto.CourseDTO;
import com.project.professor.allocation.model.Course;

@Component
public class CourseMapper {

	private ModelMapper modelMapper;

	public CourseMapper() {
		this.modelMapper = new ModelMapper();
	}

	public List<CourseBaseDTO> toCourseBaseDTO(List<Course> courses) {
		return courses.stream().map(this::toCourseBaseDTO).collect(Collectors.toList());
	}

	public CourseBaseDTO toCourseBaseDTO(Course course) {
		return modelMapper.map(course, CourseBaseDTO.class);
	}

	public CourseDTO toCourseDTO(Course course) {
		return modelMapper.map(course, CourseDTO.class);
	}

	public Course toCourse(CourseBaseDTO courseBaseDTO) {
		return modelMapper.map(courseBaseDTO, Course.class);
	}
}
