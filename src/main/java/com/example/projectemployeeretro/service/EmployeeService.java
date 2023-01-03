package com.example.projectemployeeretro.service;

import com.example.projectemployeeretro.dto.EmployeeCreationDTO;
import com.example.projectemployeeretro.dto.EmployeeDTO;
import com.example.projectemployeeretro.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployee();

    ResponseEntity<?> saveEmployee(EmployeeCreationDTO dto);

    ResponseEntity<?> updateEmployee(EmployeeCreationDTO dto, Long id);

    ResponseEntity<?> deleteEmployee(Long id);
    Employee getEmployeeById(Long id);

    List<EmployeeDTO> getNccUser()throws JsonProcessingException;
}
