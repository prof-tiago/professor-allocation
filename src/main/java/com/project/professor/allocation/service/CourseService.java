package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.project.professor.allocation.model.Course;
import com.project.professor.allocation.repository.CourseRepository;

@Service
public class CourseService {

	private CourseRepository courseRepository;

	public CourseService(CourseRepository courseRepository) {
		super();
		this.courseRepository = courseRepository;
	}

	public List<Course> findAll(String name) {
		if (name == null) {
			return courseRepository.findAll();
		} else {
			return courseRepository.findByNameContainingIgnoreCase(name);
		}
	}

	public Course findById(Long id) {
		return courseRepository.findById(id).orElse(null);
	}

	public Course save(Course course) {
		course.setId(null);
		return internalSave(course);
	}

	public Course update(Course course) {
		Long id = course.getId();
		if (id == null) {
			return null;
		}

		Course findedCourse = courseRepository.findById(id).orElse(null);
		if (findedCourse == null) {
			return null;
		}

		return internalSave(course);
	}

	public void deleteById(Long id) {
		try {
			courseRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
	}

	public void deleteAll() {
		courseRepository.deleteAllInBatch();
	}

	private Course internalSave(Course course) {
		try {
			return courseRepository.save(course);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
