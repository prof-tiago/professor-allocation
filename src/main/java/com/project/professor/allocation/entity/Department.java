package com.project.professor.allocation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "department")

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Department {

    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;
}
