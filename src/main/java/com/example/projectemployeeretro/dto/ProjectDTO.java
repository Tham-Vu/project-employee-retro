package com.example.projectemployeeretro.dto;

import com.example.projectemployeeretro.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO implements Serializable {
    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
//    private List<Long> employeeIds;
}
