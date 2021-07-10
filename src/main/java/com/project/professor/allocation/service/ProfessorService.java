package com.project.professor.allocation.service;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.exception.EntityInstanceNotFoundException;
import com.project.professor.allocation.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

@RequiredArgsConstructor
@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final DepartmentService departmentService;

    public List<Professor> findAll(String name) {
        if (name == null) {
            return professorRepository.findAll();
        } else {
            return professorRepository.findByNameContainingIgnoreCase(name);
        }
    }

    public Professor findById(Long id) {
        return professorRepository.findById(id).orElseThrow(getEntityInstanceNotFoundExceptionSupplier(id));
    }

    public List<Professor> findByDepartment(Long departmentId) {
        return professorRepository.findByDepartmentId(departmentId);
    }

    public Professor save(Professor professor) {
        professor.setId(null);
        return saveInternal(professor);
    }

    public Professor update(Professor professor) {
        Long id = professor.getId();
        if (id != null && professorRepository.existsById(id)) {
            return saveInternal(professor);
        } else {
            throw getEntityInstanceNotFoundExceptionSupplier(id).get();
        }
    }

    public void deleteById(Long id) {
        if (id != null && professorRepository.existsById(id)) {
            professorRepository.deleteById(id);
        }
    }

    public void deleteAll() {
        professorRepository.deleteAllInBatch();
    }

    private Professor saveInternal(Professor professor) {
        professor = professorRepository.save(professor);

        Department department = professor.getDepartment();
        department = departmentService.findById(department.getId());
        professor.setDepartment(department);
        professor.setAllocations(Collections.emptyList());

        return professor;
    }

    private Supplier<EntityInstanceNotFoundException> getEntityInstanceNotFoundExceptionSupplier(Long id) {
        return () -> new EntityInstanceNotFoundException(Professor.class, id);
    }
}
