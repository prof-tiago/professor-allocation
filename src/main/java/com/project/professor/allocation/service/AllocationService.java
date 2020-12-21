package com.project.professor.allocation.service;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.AllocationRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllocationService {

    private final AllocationRepository allocationRepository;
    private final ProfessorService professorService;
    private final CourseService courseService;

    public AllocationService(AllocationRepository allocationRepository, ProfessorService professorService,
                             CourseService courseService) {
        super();
        this.allocationRepository = allocationRepository;
        this.professorService = professorService;
        this.courseService = courseService;
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

    public Allocation save(Allocation allocation) {
        allocation.setId(null);
        return saveInternal(allocation);
    }

    public Allocation update(Allocation allocation) {
        Long id = allocation.getId();
        if (id == null) {
            return null;
        }

        if (!allocationRepository.existsById(id)) {
            return null;
        }

        return saveInternal(allocation);
    }

    public void deleteById(Long id) {
        if (id != null && allocationRepository.existsById(id)) {
            allocationRepository.deleteById(id);
        }
    }

    public void deleteAll() {
        allocationRepository.deleteAllInBatch();
    }

    private Allocation saveInternal(Allocation allocation) {
        try {
            if (!hasCollision(allocation)) {
                allocation = allocationRepository.save(allocation);

                Professor professor = allocation.getProfessor();
                professor = professorService.findById(professor.getId());
                allocation.setProfessor(professor);

                Course course = allocation.getCourse();
                course = courseService.findById(course.getId());
                allocation.setCourse(course);

                return allocation;
            }
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
        }

        return null;
    }

    private boolean hasCollision(Allocation newAllocation) {
        boolean hasCollision = false;

        List<Allocation> currentAllocations = allocationRepository.findByProfessorId(newAllocation.getProfessor().getId());

        for (Allocation currentAllocation : currentAllocations) {
            hasCollision = hasCollision(currentAllocation, newAllocation);
            if (hasCollision) {
                break;
            }
        }

        return hasCollision;
    }

    private boolean hasCollision(Allocation currentAllocation, Allocation newAllocation) {
        return !currentAllocation.getId().equals(newAllocation.getId())
                && currentAllocation.getDayOfWeek() == newAllocation.getDayOfWeek()
                && currentAllocation.getStartHour().compareTo(newAllocation.getEndHour()) < 0
                && newAllocation.getStartHour().compareTo(currentAllocation.getEndHour()) < 0;
    }
}
