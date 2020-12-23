package com.project.professor.allocation.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class AllocationCreationDTO extends AllocationBaseDTO {

    private Long professorId;

    private Long courseId;
}
