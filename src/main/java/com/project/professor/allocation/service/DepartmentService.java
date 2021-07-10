package com.project.professor.allocation.service;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.exception.EntityInstanceNotFoundException;
import com.project.professor.allocation.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

@RequiredArgsConstructor
@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public List<Department> findAll(String name) {
        if (name == null) {
            return departmentRepository.findAll();
        } else {
            return departmentRepository.findByNameContainingIgnoreCase(name);
        }
    }

    public Department findById(Long id) {
        return departmentRepository.findById(id).orElseThrow(getEntityInstanceNotFoundExceptionSupplier(id));
    }

    public Department save(Department department) {
        department.setId(null);
        return saveInternal(department);
    }

    public Department update(Department department) {
        Long id = department.getId();
        if (id != null && departmentRepository.existsById(id)) {
            return saveInternal(department);
        } else {
            throw getEntityInstanceNotFoundExceptionSupplier(id).get();
        }
    }

    public void deleteById(Long id) {
        if (id != null && departmentRepository.existsById(id)) {
            departmentRepository.deleteById(id);
        }
    }

    public void deleteAll() {
        departmentRepository.deleteAllInBatch();
    }

    private Department saveInternal(Department department) {
        department = departmentRepository.save(department);
        department.setProfessors(Collections.emptyList());
        return department;
    }

    private Supplier<EntityInstanceNotFoundException> getEntityInstanceNotFoundExceptionSupplier(Long id) {
        return () -> new EntityInstanceNotFoundException(Department.class, id);
    }
}
