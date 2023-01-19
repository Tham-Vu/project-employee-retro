package com.example.projectemployeeretro.service;

import com.example.projectemployeeretro.dto.EmployeeProjectCreationDTO;
import com.example.projectemployeeretro.dto.EmployeeProjectDTO;
import com.example.projectemployeeretro.entity.EmployeeProject;

import java.util.List;

public interface EmployeeProjectService {
    List<EmployeeProjectDTO> getAll();
    EmployeeProjectDTO create(EmployeeProjectCreationDTO dto);
}
