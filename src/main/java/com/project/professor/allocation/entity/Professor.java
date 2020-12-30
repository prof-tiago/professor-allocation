package com.project.professor.allocation.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "professor")

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cpf", unique = true, nullable = false)
    private String cpf;

    @ManyToOne(optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "professor")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Allocation> allocations;
}
