package com.project.professor.allocation.service;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.AllocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllocationService {

    private final AllocationRepository allocationRepository;

    public AllocationService(AllocationRepository allocationRepository) {
        super();
        this.allocationRepository = allocationRepository;
    }

    public List<Allocation> findAll() {
        return allocationRepository.findAll();
    }

    public Allocation findById(Long id) {
        return allocationRepository.findById(id).orElse(null);
    }

    public List<Allocation> findByProfessor(Long professorId) {
        return allocationRepository.findByProfessorId(professorId);
    }

    public List<Allocation> findByCourse(Long courseId) {
        return allocationRepository.findByCourseId(courseId);
    }
}
