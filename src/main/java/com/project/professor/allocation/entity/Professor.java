package com.project.professor.allocation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "professor")

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Professor {

    private Long id;

    private String name;

    private String cpf;
}
