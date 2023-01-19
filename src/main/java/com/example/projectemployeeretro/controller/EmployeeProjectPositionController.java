package com.example.projectemployeeretro.controller;

import com.example.projectemployeeretro.dto.EmployeeProjectCreationDTO;
import com.example.projectemployeeretro.service.EmployeeProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee-project-position")
public class EmployeeProjectPositionController {
    @Autowired
    private EmployeeProjectService service;
    @Autowired
    private ModelMapper mapper;
    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody EmployeeProjectCreationDTO dto) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }
}
