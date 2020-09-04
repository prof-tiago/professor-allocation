package com.project.professor.allocation.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.project.professor.allocation.dto.ProfessorBaseDTO;
import com.project.professor.allocation.dto.ProfessorDTO;
import com.project.professor.allocation.model.Professor;

@Component
public class ProfessorMapper {

	private ModelMapper modelMapper;

	public ProfessorMapper() {
		this.modelMapper = new ModelMapper();
	}

	public List<ProfessorBaseDTO> toProfessorBaseDTO(List<Professor> professors) {
		return professors.stream().map(this::toProfessorBaseDTO).collect(Collectors.toList());
	}

	public ProfessorBaseDTO toProfessorBaseDTO(Professor professor) {
		return modelMapper.map(professor, ProfessorBaseDTO.class);
	}

	public ProfessorDTO toProfessorDTO(Professor professor) {
		return modelMapper.map(professor, ProfessorDTO.class);
	}

	public Professor toProfessor(ProfessorBaseDTO professorBaseDTO) {
		return modelMapper.map(professorBaseDTO, Professor.class);
	}
}
