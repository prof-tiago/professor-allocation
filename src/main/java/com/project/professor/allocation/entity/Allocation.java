package com.project.professor.allocation.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.Date;

@Entity
@Table(name = "allocation")

@NoArgsConstructor
@Data
public class Allocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "day", nullable = false)
    private DayOfWeek dayOfWeek;

    @JsonFormat(pattern = "HH:mmZ")
    @Temporal(TemporalType.TIME)
    @Column(name = "start", nullable = false)
    private Date startHour;

    @JsonFormat(pattern = "HH:mmZ")
    @Temporal(TemporalType.TIME)
    @Column(name = "end", nullable = false)
    private Date endHour;

    @JsonIgnoreProperties({"allocations"})
    @ManyToOne(optional = false)
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;

    @JsonIgnoreProperties({"allocations"})
    @ManyToOne(optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
}
