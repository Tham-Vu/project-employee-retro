package com.example.projectemployeeretro.service;

import com.example.projectemployeeretro.dto.EmployeeCreationDTO;
import com.example.projectemployeeretro.entity.Employee;
import org.springframework.http.ResponseEntity;

public interface EmployeeService {

    ResponseEntity<?> getAllEmployee();

    ResponseEntity<?> saveEmployee(EmployeeCreationDTO dto);

    ResponseEntity<?> updateEmployee(EmployeeCreationDTO dto, Long id);

    ResponseEntity<?> deleteEmployee(Long id);
    Employee getEmployeeById(Long id);

}
