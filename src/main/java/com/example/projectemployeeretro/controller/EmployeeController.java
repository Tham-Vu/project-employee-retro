package com.example.projectemployeeretro.controller;

import com.example.projectemployeeretro.dto.EmployeeCreationDTO;
import com.example.projectemployeeretro.restemplate_webclient.EmployeeClient;
import com.example.projectemployeeretro.restemplate_webclient.EmployeeClientList;
import com.example.projectemployeeretro.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    WebClient webClient;
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
    @GetMapping("/client-ncc")
    public ResponseEntity<?> findAll()throws JsonProcessingException{
        return ResponseEntity.status(HttpStatus.OK).body(service.getNccUser());
    }
    @GetMapping("/client-ncc1")
    public Mono<EmployeeClientList> getEmployees(){
        return webClient.get()
                .uri("/")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve().bodyToMono(EmployeeClientList.class);
    }
}
