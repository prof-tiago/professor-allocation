package com.project.professor.allocation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Allocation {

    private Long id;

    private DayOfWeek dayOfWeek;

    private Date startHour;

    private Date endHour;
}
