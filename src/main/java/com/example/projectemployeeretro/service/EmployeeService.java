package com.example.projectemployeeretro.service;

import com.example.projectemployeeretro.entity.Employee;
import com.example.projectemployeeretro.entity.Project;
import com.example.projectemployeeretro.repository.EmployeeRepository;
import com.example.projectemployeeretro.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ProjectRepository projectRepository;
    public ResponseEntity<?> saveEmployee(Employee employee){
        Employee insertEmployee = new Employee();
        if (employeeRepository.findByEmail(employee.getEmail()).isPresent()){
            return ResponseEntity.badRequest().body("The email is already present, failed to create new employee");
        }else {
            insertEmployee.setFullName(employee.getFullName());
            insertEmployee.setEmail(employee.getEmail());
            insertEmployee.setBirthDay(employee.getBirthDay());
            insertEmployee.setUser_role(employee.getUser_role());
            Employee saveEmployee = employeeRepository.save(insertEmployee);
            if (employeeRepository.findById(saveEmployee.getId()).isPresent())
                return ResponseEntity.ok("Employee created successfully");
            else return ResponseEntity.unprocessableEntity().body("Failed creating employee as specified");
        }
    }
}
