package com.project.professor.allocation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.util.Date;

@NoArgsConstructor
@Data
public class AllocationBaseDTO {

    private Long id;

    private DayOfWeek dayOfWeek;

    @ApiModelProperty(example = "HH:mmZ")
    @JsonFormat(pattern = "HH:mmZ")
    private Date startHour;

    @ApiModelProperty(example = "HH:mmZ")
    @JsonFormat(pattern = "HH:mmZ")
    private Date endHour;
}
