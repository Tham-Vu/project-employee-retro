package com.example.projectemployeeretro.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeProjectCreationDTO {
    private Long employee_id;
    private Long project_id;
    private Set<Long> position_id;
}
