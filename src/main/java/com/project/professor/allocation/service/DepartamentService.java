package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.project.professor.allocation.model.Departament;
import com.project.professor.allocation.repository.DepartamentRepository;

@Service
public class DepartamentService {

	private DepartamentRepository departamentRepository;

	public DepartamentService(DepartamentRepository departamentRepository) {
		super();
		this.departamentRepository = departamentRepository;
	}

	public List<Departament> findAll(String name) {
		if (name == null) {
			return departamentRepository.findAll();
		} else {
			return departamentRepository.findByNameContainingIgnoreCase(name);
		}
	}

	public Departament findById(Long id) {
		return departamentRepository.findById(id).orElse(null);
	}

	public Departament save(Departament departament) {
		departament.setId(null);
		return internalSave(departament);
	}

	public Departament update(Departament departament) {
		Long id = departament.getId();
		if (id == null) {
			return null;
		}

		Departament findedDepartament = departamentRepository.findById(id).orElse(null);
		if (findedDepartament == null) {
			return null;
		}

		return internalSave(departament);
	}

	public void deleteById(Long id) {
		try {
			departamentRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
	}

	public void deleteAll() {
		departamentRepository.deleteAllInBatch();
	}

	private Departament internalSave(Departament departament) {
		try {
			return departamentRepository.save(departament);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
