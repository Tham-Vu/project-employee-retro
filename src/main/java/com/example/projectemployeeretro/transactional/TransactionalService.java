package com.example.projectemployeeretro.transactional;

import com.example.projectemployeeretro.dto.EmployeeDTO;
import com.example.projectemployeeretro.entity.CustomUserDetails;
import com.example.projectemployeeretro.entity.Employee;
import com.example.projectemployeeretro.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TransactionalService {
    @Autowired
    private EmployeeRepository repository;
    @Autowired
    private ModelMapper mapper;
    @Transactional
    public ResponseEntity<?> getEvenEmployeeID(Long id, String name){
        Employee employee = null;
        if (id % 2 == 0) {
                employee = repository.findById(id).get();
                employee.setUsername(name);
        }
        return ResponseEntity.status(HttpStatus.OK).body(mapper.map(employee, EmployeeDTO.class));
    }
}
