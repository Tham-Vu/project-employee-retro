package com.example.projectemployeeretro.controller;

import com.example.projectemployeeretro.dto.PositionDTO;
import com.example.projectemployeeretro.service.PositionService;
import com.example.projectemployeeretro.service.PositionServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/positions")
public class PositionController {
//    @Autowired
    private PositionService service;

    public PositionController(PositionService service) {
        this.service = service;
    }

//    @Autowired
    private ModelMapper mapper;

    public void setMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody PositionDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }
}
