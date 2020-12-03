package com.project.professor.allocation.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "professor")

@NoArgsConstructor
@Data
public class Professor {

    private Long id;

    private String name;

    private String cpf;
}
