package com.project.professor.allocation.service;

import com.project.professor.allocation.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        super();
        this.professorRepository = professorRepository;
    }
}
