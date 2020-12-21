package com.project.professor.allocation.service;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.ProfessorRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final DepartmentService departmentService;

    public ProfessorService(ProfessorRepository professorRepository, DepartmentService departmentService) {
        super();
        this.professorRepository = professorRepository;
        this.departmentService = departmentService;
    }

    public List<Professor> findAll(String name) {
        if (name == null) {
            return professorRepository.findAll();
        } else {
            return professorRepository.findByNameContainingIgnoreCase(name);
        }
    }

    public Professor findById(Long id) {
        return professorRepository.findById(id).orElse(null);
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
        if (id == null) {
            return null;
        }

        if (!professorRepository.existsById(id)) {
            return null;
        }

        return saveInternal(professor);
    }

    private Professor saveInternal(Professor professor) {
        try {
            professor = professorRepository.save(professor);

            Department department = professor.getDepartment();
            department = departmentService.findById(department.getId());
            professor.setDepartment(department);

            return professor;
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
