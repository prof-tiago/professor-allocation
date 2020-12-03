package com.project.professor.allocation.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.DayOfWeek;
import java.util.Date;

@Entity
@Table(name = "allocation")

@NoArgsConstructor
@Data
public class Allocation {

    private Long id;

    private DayOfWeek dayOfWeek;

    private Date startHour;

    private Date endHour;
}
