package com.example.projectemployeeretro.service;

import com.example.projectemployeeretro.dto.EmployeeCreationDTO;
import com.example.projectemployeeretro.dto.EmployeeDTO;
import com.example.projectemployeeretro.entity.Employee;
import com.example.projectemployeeretro.query.CountEmployeeByRoleName;
import com.example.projectemployeeretro.query.CountEmployeeRole;
import com.example.projectemployeeretro.query.ICountEmployeeRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDTO> getAllEmployee();
    EmployeeDTO getById(Long id);
//    List<EmployeeCreationDTO> getAllEmployee2();

    ResponseEntity<?> saveEmployee(EmployeeCreationDTO dto);

    ResponseEntity<?> updateEmployee(EmployeeCreationDTO dto, Long id);

    ResponseEntity<?> deleteEmployee(Long id);
    Employee getEmployeeById(Long id);

    Employee findById(Long id);
    Page<Employee> findAllEmployeeWithPagination(Pageable pageable);
    Slice<Employee> findAllEmployeeWithSlice(Pageable pageable);
    Employee findEmployeeByUsername(String userName);
    List<String> getAllEmail();
    List<CountEmployeeRole> countTotalEmployeeByRole();
    List<ICountEmployeeRole> countTotalEmployeeByRoleInterface();
    List<CountEmployeeByRoleName> getEmployeeByRoleName();
    Employee updatePassWord(Long id, String password);
}
