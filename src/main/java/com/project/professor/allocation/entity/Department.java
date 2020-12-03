package com.project.professor.allocation.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "department")

@NoArgsConstructor
@Data
public class Department {

    private Long id;

    private String name;
}
