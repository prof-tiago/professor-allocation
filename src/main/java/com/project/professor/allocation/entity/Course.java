package com.project.professor.allocation.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "course")

@NoArgsConstructor
@Data
public class Course {

    private Long id;

    private String name;
}
