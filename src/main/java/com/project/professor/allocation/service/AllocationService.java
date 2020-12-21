package com.project.professor.allocation.service;

import com.project.professor.allocation.repository.AllocationRepository;
import org.springframework.stereotype.Service;

@Service
public class AllocationService {

    private final AllocationRepository allocationRepository;

    public AllocationService(AllocationRepository allocationRepository) {
        super();
        this.allocationRepository = allocationRepository;
    }
}
