package com.example.projectemployeeretro.service;

import com.example.projectemployeeretro.dto.EmployeeCreationDTO;
import com.example.projectemployeeretro.dto.EmployeeDTO;
import com.example.projectemployeeretro.entity.Employee;
import com.example.projectemployeeretro.query.CountEmployeeByRoleName;
import com.example.projectemployeeretro.query.CountEmployeeRole;
import com.example.projectemployeeretro.query.ICountEmployeeRole;
import com.example.projectemployeeretro.repository.EmployeeProjectRepo;
import com.example.projectemployeeretro.repository.EmployeeRepository;
import com.example.projectemployeeretro.repository.ProjectRepository;
import com.example.projectemployeeretro.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;

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
    private EmployeeProjectRepo employeeProjectRepo;
    @Autowired
    private ModelMapper mapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeDTO> getAllEmployee(){
        List<Employee> employees = employeeRepository.findAll();
//        employees.stream().forEach(e -> e.getEmployeeProjects());
        return employees.stream().map(e->mapper.map(e, EmployeeDTO.class)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getById(Long id) {
        return mapper.map(employeeRepository.findById(id), EmployeeDTO.class);
    }

    @Override
    public Page<Employee> findAllEmployeeWithPagination(Pageable pageable){
        return employeeRepository.findAllEmployeeWithPagination(pageable);

    }
    @Override
    public Slice<Employee> findAllEmployeeWithSlice(Pageable pageable){
        return employeeRepository.findAllEmployeeWithSlice(pageable);
    }

    @Override
    public Employee findEmployeeByUsername(String userName) {
        return null;
    }

    @Override
    public List<String> getAllEmail(){
        return employeeRepository.getAllEmail();
    }
    @Override
    public List<CountEmployeeRole> countTotalEmployeeByRole(){
        return employeeRepository.countTotalEmployeeByRole();
    }
    @Override
    public List<ICountEmployeeRole> countTotalEmployeeByRoleInterface(){
        return employeeRepository.countTotalEmployeeByRoleInterface();
    }
    @Override
    public List<CountEmployeeByRoleName> getEmployeeByRoleName(){
        return employeeRepository.getEmployeeByRoleName();
    }
    @Override
    public Employee findById(Long id){
        return employeeRepository.findById(id).get();
    }
    @Override
    public ResponseEntity<?> saveEmployee(EmployeeCreationDTO dto){
        Employee employee = mapper.map(dto, Employee.class);
        employee.setPassword(passwordEncoder.encode(dto.getPassword()));
        employee.setRole(roleRepository.findById(dto.getRoleId()).get());
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeRepository.save(employee));
    }
    @Override
    public ResponseEntity<?> updateEmployee(EmployeeCreationDTO dto, Long id){
        Employee updateEmployee = employeeRepository.findById(id)
                .map(employee -> {
                    employee.setId(id);
                    employee.setFullName(dto.getFullName());
                    employee.setEmail(dto.getEmail());
                    employee.setBirthday(dto.getBirthday());
                    employee.setUsername(dto.getUsername());
                    employee.setRole(roleRepository.findById(dto.getRoleId()).orElse(null));
                    return employeeRepository.save(employee);
                }).orElseGet(()->{
                    Employee employee = mapper.map(dto, Employee.class);
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
    @Override
    public Employee updatePassWord(Long id, String password) {
        Employee employee = employeeRepository.findById(id).get();
        employee.setPassword(passwordEncoder.encode(password));
        employeeRepository.save(employee);
        return employee;
    }


}
