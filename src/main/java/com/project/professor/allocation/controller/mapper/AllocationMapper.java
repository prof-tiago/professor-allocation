package com.project.professor.allocation.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.project.professor.allocation.dto.AllocationBaseDTO;
import com.project.professor.allocation.dto.AllocationDTO;
import com.project.professor.allocation.model.Allocation;

@Component
public class AllocationMapper {

	private ModelMapper modelMapper;

	public AllocationMapper() {
		this.modelMapper = new ModelMapper();
	}

	public List<AllocationDTO> toAllocationDTO(List<Allocation> allocations) {
		return allocations.stream().map(this::toAllocationDTO).collect(Collectors.toList());
	}

	public AllocationBaseDTO toAllocationBaseDTO(Allocation allocation) {
		return modelMapper.map(allocation, AllocationBaseDTO.class);
	}

	public AllocationDTO toAllocationDTO(Allocation allocation) {
		return modelMapper.map(allocation, AllocationDTO.class);
	}

	public Allocation toAllocation(AllocationBaseDTO allocationBaseDTO) {
		return modelMapper.map(allocationBaseDTO, Allocation.class);
	}
}
