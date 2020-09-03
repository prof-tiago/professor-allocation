package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.project.professor.allocation.model.Allocation;
import com.project.professor.allocation.model.Course;
import com.project.professor.allocation.model.Professor;
import com.project.professor.allocation.repository.AllocationRepository;

@Service
public class AllocationService {

	private AllocationRepository allocationRepository;
	private ProfessorService professorService;
	private CourseService courseService;

	public AllocationService(AllocationRepository allocationRepository, ProfessorService professorService,
			CourseService courseService) {
		super();
		this.allocationRepository = allocationRepository;
		this.professorService = professorService;
		this.courseService = courseService;
	}

	public List<Allocation> findAll() {
		return allocationRepository.findAll();
	}

	public List<Allocation> findByProfessor(Long id) {
		Professor professor = new Professor();
		professor.setId(id);
		return allocationRepository.findByProfessor(professor);
	}

	public List<Allocation> findByCourse(Long id) {
		Course course = new Course();
		course.setId(id);
		return allocationRepository.findByCourse(course);
	}

	public Allocation findById(Long id) {
		return allocationRepository.findById(id).orElse(null);
	}

	public Allocation save(Allocation allocation) {
		allocation.setId(null);
		return internalSave(allocation);
	}

	public Allocation update(Allocation allocation) {
		Long id = allocation.getId();
		if (id == null) {
			return null;
		}

		Allocation findedAllocation = allocationRepository.findById(id).orElse(null);
		if (findedAllocation == null) {
			return null;
		}

		return internalSave(allocation);
	}

	public void deleteById(Long id) {
		try {
			allocationRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
	}

	public void deleteAll() {
		allocationRepository.deleteAllInBatch();
	}

	private Allocation internalSave(Allocation allocation) {
		try {
			if (!hasCollision(allocation)) {
				allocation = allocationRepository.save(allocation);
				if (allocation != null) {
					Professor professor = allocation.getProfessor();
					professor = professorService.findById(professor.getId());
					allocation.setProfessor(professor);

					Course course = allocation.getCourse();
					course = courseService.findById(course.getId());
					allocation.setCourse(course);
				}
				return allocation;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private boolean hasCollision(Allocation newAllocation) {
		boolean hasCollision = false;

		List<Allocation> currentAllocations = allocationRepository.findByProfessor(newAllocation.getProfessor());

		for (Allocation currentAllocation : currentAllocations) {
			hasCollision = hasCollision(currentAllocation, newAllocation);
			if (hasCollision) {
				break;
			}
		}

		return hasCollision;
	}

	private boolean hasCollision(Allocation currentAllocation, Allocation newAllocation) {
		return currentAllocation.getDayOfWeek() == newAllocation.getDayOfWeek()
				&& currentAllocation.getStartHour().compareTo(newAllocation.getEndHour()) < 0
				&& newAllocation.getStartHour().compareTo(currentAllocation.getEndHour()) < 0;
	}
}
