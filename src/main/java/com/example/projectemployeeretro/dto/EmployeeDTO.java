package com.example.projectemployeeretro.dto;

import com.example.projectemployeeretro.entity.EmployeeProject;
import com.example.projectemployeeretro.entity.Project;
import com.example.projectemployeeretro.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeDTO implements Serializable {
    private Long id;
    private String fullName;
    private String email;
    private LocalDate birthday;
    private String username;
    private Long roleId;
    private Set<ProjectPositionDTO> employeeProjects;
}