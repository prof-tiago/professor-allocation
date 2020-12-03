package com.project.professor.allocation.entity;

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

    @Temporal(TemporalType.TIME)
    @Column(name = "start", nullable = false)
    private Date startHour;

    @Temporal(TemporalType.TIME)
    @Column(name = "end", nullable = false)
    private Date endHour;
}
