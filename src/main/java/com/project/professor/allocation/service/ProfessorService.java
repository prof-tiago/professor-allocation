package com.project.professor.allocation.service;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        super();
        this.professorRepository = professorRepository;
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
}
