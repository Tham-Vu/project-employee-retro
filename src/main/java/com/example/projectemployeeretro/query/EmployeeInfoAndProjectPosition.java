package com.example.projectemployeeretro.query;

import com.example.projectemployeeretro.dto.EmployeeProjectDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class EmployeeInfoAndProjectPosition {
    private Long id;
    private String fullName;
    private String email;
    private LocalDate birthday;
    private String username;
    private Long roleId;
    private Set<EmployeeProjectDTO> employeeProjectDTOS;
}
