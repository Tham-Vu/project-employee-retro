package com.example.projectemployeeretro.controller;

import com.example.projectemployeeretro.entity.Employee;
import com.example.projectemployeeretro.entity.Project;
import com.example.projectemployeeretro.repository.EmployeeRepository;
import com.example.projectemployeeretro.repository.ProjectRepository;
import com.example.projectemployeeretro.service.EmployeeService;
import com.example.projectemployeeretro.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepository repository;
    @Autowired
    private EmployeeService employeeService;
    @GetMapping
    public ResponseEntity<?> getAllEmployee(){
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id){
        Optional<Employee> employee = repository.findById(id);
        if (employee.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(employee);
        }else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cannot find employee with id " + id);
    }
    @PostMapping("/insert")
    public ResponseEntity<?> insertEmployee(@RequestBody Employee employee){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.saveEmployee(employee));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEmployee(@RequestBody Employee newEmployee, @PathVariable Long id){
        Employee updateEmployee = repository.findById(id)
                .map(employee -> {
                    employee.setFullName(newEmployee.getFullName());
                    employee.setEmail(newEmployee.getEmail());
                    employee.setBirthDay(newEmployee.getBirthDay());
                    employee.setUser_role(newEmployee.getUser_role());
                    return repository.save(employee);
                }).orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(updateEmployee));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id){
        boolean exists = repository.existsById(id);
        if (exists){
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Delete employee with id " + id + " successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Cannot delete");
    }
}
