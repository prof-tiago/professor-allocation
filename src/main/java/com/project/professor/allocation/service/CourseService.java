package com.project.professor.allocation.service;

import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.repository.CourseRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

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
        return saveInternal(course);
    }

    public Course update(Course course) {
        Long id = course.getId();
        if (id == null) {
            return null;
        }

        if (!courseRepository.existsById(id)) {
            return null;
        }

        return saveInternal(course);
    }

    private Course saveInternal(Course course) {
        try {
            return courseRepository.save(course);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
