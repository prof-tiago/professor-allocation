package com.project.professor.allocation.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.util.Date;

@NoArgsConstructor
@Data
public class AllocationBaseDTO {

    private Long id;

    private DayOfWeek dayOfWeek;

    private Date startHour;

    private Date endHour;
}
