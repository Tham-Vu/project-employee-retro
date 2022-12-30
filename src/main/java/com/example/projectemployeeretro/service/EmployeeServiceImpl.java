package com.example.projectemployeeretro.service;

import com.example.projectemployeeretro.dto.EmployeeCreationDTO;
import com.example.projectemployeeretro.dto.EmployeeDTO;
import com.example.projectemployeeretro.entity.Employee;
import com.example.projectemployeeretro.entity.Project;
import com.example.projectemployeeretro.entity.Role;
import com.example.projectemployeeretro.repository.EmployeeRepository;
import com.example.projectemployeeretro.repository.ProjectRepository;
import com.example.projectemployeeretro.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ModelMapper mapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDTO conversionToDto(Long id){
        Employee employee = employeeRepository.findById(id).orElse(null);
        EmployeeDTO employeeDTO = mapper.map(employee, EmployeeDTO.class);
        return employeeDTO;
    }
    public Employee conversionToEntity(EmployeeCreationDTO employeeDTO){
        Employee employee = mapper.map(employeeDTO, Employee.class);
        return employee;
    }
    @Override
    public ResponseEntity<?> getAllEmployee(){
//        List<Employee> employees = employeeRepository.findAll();
//        List<EmployeeDTO> employeeDTOS = conversionToDto(employees);
//        return ResponseEntity.status(HttpStatus.OK).body(employeeDTOS);
        return null;
    }
    @Override
    public ResponseEntity<?> saveEmployee(EmployeeCreationDTO dto){
        Employee employee = conversionToEntity(dto);
        Set<Project> projects =  new HashSet<>();
        projects.addAll(projectRepository.findAllById(dto.getProjectId()));
        employee.setProjects(projects);
        employee.setPassword(passwordEncoder.encode(dto.getPassword()));
        Role role = roleRepository.findById(dto.getRole_id()).orElse(null);
        employee.setRole(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeRepository.save(employee));
    }
    @Override
    public ResponseEntity<?> updateEmployee(EmployeeCreationDTO dto, Long id){
        Employee updateEmployee = employeeRepository.findById(id)
                .map(employee -> {
                    employee.setFullName(dto.getFullName());
                    employee.setEmail(dto.getEmail());
                    employee.setBirthday(dto.getBirthday());
                    employee.setUsername(dto.getUsername());
                    employee.setRole(roleRepository.findById(dto.getRole_id()).orElse(null));
                    employee.setProjects((Set<Project>) projectRepository.findAllById(dto.getProjectId()));
                    return employeeRepository.save(employee);
                }).orElseGet(()->{
                    Employee employee = conversionToEntity(dto);
                    employee.setId(id);
                    return employeeRepository.save(employee);
                });
        return ResponseEntity.status(HttpStatus.OK).body(employeeRepository.save(updateEmployee));
    }
    @Override
    public ResponseEntity<?> deleteEmployee(Long id){
        boolean isExist = employeeRepository.existsById(id);
        if(!isExist){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        }else {
            employeeRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Delete Successfully");
        }
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).get();
    }
}
