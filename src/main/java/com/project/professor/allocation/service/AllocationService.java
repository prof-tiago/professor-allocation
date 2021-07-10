package com.project.professor.allocation.service;

import com.project.professor.allocation.repository.AllocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AllocationService {

    private final AllocationRepository allocationRepository;

}
