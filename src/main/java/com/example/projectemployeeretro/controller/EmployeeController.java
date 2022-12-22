package com.example.projectemployeeretro.controller;

import com.example.projectemployeeretro.dto.EmployeeCreationDTO;
import com.example.projectemployeeretro.service.EmployeeService;
import com.example.projectemployeeretro.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService service;
    @GetMapping(value = {"/home"})
    public String hompage(){
        return "Home";
    }
    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }
    @GetMapping()
    public ResponseEntity<?> getAllEmployee(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllEmployee());
    }
    @PostMapping("/insert")
    public ResponseEntity<?> insertEmployee(@RequestBody EmployeeCreationDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveEmployee(dto));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEmployee(@RequestBody EmployeeCreationDTO dto, @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.RESET_CONTENT).body(service.updateEmployee(dto, id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.MULTI_STATUS).body(service.deleteEmployee(id));
    }
}
