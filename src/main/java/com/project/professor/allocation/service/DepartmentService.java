package com.project.professor.allocation.service;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.repository.DepartmentRepository;
import org.springframework.dao.DataIntegrityViolationException;
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

    public Department save(Department department) {
        department.setId(null);
        return saveInternal(department);
    }

    public Department update(Department department) {
        Long id = department.getId();
        if (id == null) {
            return null;
        }

        if (!departmentRepository.existsById(id)) {
            return null;
        }

        return saveInternal(department);
    }

    private Department saveInternal(Department department) {
        try {
            return departmentRepository.save(department);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
