package com.project.professor.allocation.service;

import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.exception.EntityInstanceNotFoundException;
import com.project.professor.allocation.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

@RequiredArgsConstructor
@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public List<Course> findAll(String name) {
        if (name == null) {
            return courseRepository.findAll();
        } else {
            return courseRepository.findByNameContainingIgnoreCase(name);
        }
    }

    public Course findById(Long id) {
        return courseRepository.findById(id).orElseThrow(getEntityInstanceNotFoundExceptionSupplier(id));
    }

    public Course save(Course course) {
        course.setId(null);
        return saveInternal(course);
    }

    public Course update(Course course) {
        Long id = course.getId();
        if (id != null && courseRepository.existsById(id)) {
            return saveInternal(course);
        } else {
            throw getEntityInstanceNotFoundExceptionSupplier(id).get();
        }
    }

    public void deleteById(Long id) {
        if (id != null && courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
        }
    }

    public void deleteAll() {
        courseRepository.deleteAllInBatch();
    }

    private Course saveInternal(Course course) {
        course = courseRepository.save(course);
        course.setAllocations(Collections.emptyList());
        return course;
    }

    private Supplier<EntityInstanceNotFoundException> getEntityInstanceNotFoundExceptionSupplier(Long id) {
        return () -> new EntityInstanceNotFoundException(Course.class, id);
    }
}
