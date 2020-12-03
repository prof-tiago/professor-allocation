package com.project.professor.allocation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "course")

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Course {

    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
