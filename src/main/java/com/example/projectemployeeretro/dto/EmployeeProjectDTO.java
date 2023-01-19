package com.example.projectemployeeretro.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeProjectDTO {
    private EmployeeDTO employee;
    private ProjectDTO project;
    private List<PositionDTO> position;
}
