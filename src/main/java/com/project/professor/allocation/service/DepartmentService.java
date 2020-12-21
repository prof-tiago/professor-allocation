package com.project.professor.allocation.service;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        super();
        this.departmentRepository = departmentRepository;
    }

    public List<Department> findAll(String name) {
        if (name == null) {
            return departmentRepository.findAll();
        } else {
            return departmentRepository.findByNameContainingIgnoreCase(name);
        }
    }

    public Department findById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }
}
