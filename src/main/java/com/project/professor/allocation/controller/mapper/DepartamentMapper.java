package com.project.professor.allocation.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.project.professor.allocation.dto.DepartamentBaseDTO;
import com.project.professor.allocation.dto.DepartamentDTO;
import com.project.professor.allocation.model.Departament;

@Component
public class DepartamentMapper {

	private ModelMapper modelMapper;

	public DepartamentMapper() {
		this.modelMapper = new ModelMapper();
	}

	public List<DepartamentBaseDTO> toDepartamentBaseDTO(List<Departament> departaments) {
		return departaments.stream().map(this::toDepartamentBaseDTO).collect(Collectors.toList());
	}

	public DepartamentBaseDTO toDepartamentBaseDTO(Departament departament) {
		return modelMapper.map(departament, DepartamentBaseDTO.class);
	}

	public DepartamentDTO toDepartamentDTO(Departament departament) {
		return modelMapper.map(departament, DepartamentDTO.class);
	}

	public Departament toDepartament(DepartamentBaseDTO departamentBaseDTO) {
		return modelMapper.map(departamentBaseDTO, Departament.class);
	}
}
