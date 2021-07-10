package com.project.professor.allocation.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Data
public class DepartmentBaseDTO {

    private Long id;

    @NotBlank
    @Size(max = 255)
    private String name;
}
