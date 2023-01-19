package com.example.projectemployeeretro.controller;

import com.example.projectemployeeretro.dto.EmployeeCreationDTO;
import com.example.projectemployeeretro.dto.EmployeeDTO;
import com.example.projectemployeeretro.dto.EmployeeLoginDTO;
import com.example.projectemployeeretro.restemplate_webclient.EmployeeClient;
import com.example.projectemployeeretro.restemplate_webclient.EmployeeClientList;
import com.example.projectemployeeretro.service.EmployeeService;
import com.example.projectemployeeretro.transactional.TransactionalService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    ModelMapper mapper;
    @Autowired
    private EmployeeService service;
    @GetMapping(value = {"/"})
    public String hompage(@RequestParam(name = "name", required = false)String name,  Model model){
        model.addAttribute("name", name);
        return "index";
    }
    @GetMapping("/hello")
    public String hello(){
        return "index";
    }
    @GetMapping()
    public ResponseEntity<?> getAllEmployee(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllEmployee());
    }
//    @GetMapping("")
//    public ResponseEntity<?> getEmployeeProject(){
//        return ResponseEntity.status(HttpStatus.OK).body();
//    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }
    @GetMapping("/paging")
    public ResponseEntity<?> findAllEmployeeWithPagination(@RequestParam Integer number, @RequestParam int size){
        Pageable pageable = PageRequest.of(number,size);
        return ResponseEntity.status(HttpStatus.OK).body(service.findAllEmployeeWithPagination(pageable));
    }
    @GetMapping("/slice")
    public ResponseEntity<?> findAllEmployeeWithSlice(@RequestParam Integer number){
        Pageable pageable = PageRequest.of(number,10);
        return ResponseEntity.status(HttpStatus.OK).body(service.findAllEmployeeWithSlice(pageable));
    }
    @GetMapping("/constructors")
    public ResponseEntity<?> countTotalEmployeeByRole(){
        return ResponseEntity.status(HttpStatus.OK).body(service.countTotalEmployeeByRole());
    }
    @GetMapping("/projections")
    public ResponseEntity<?> countTotalEmployeeByRoleInterface(){
        return ResponseEntity.status(HttpStatus.OK).body(service.countTotalEmployeeByRoleInterface());
    }
    @GetMapping("/join")
    public ResponseEntity<?> getEmployeeByRoleName(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getEmployeeByRoleName());
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insertEmployee(@RequestBody EmployeeCreationDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveEmployee(dto));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEmployee(@RequestBody EmployeeCreationDTO dto, @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.RESET_CONTENT).body(service.updateEmployee(dto, id));
    }
    @PatchMapping("/updatePassword/{id}")
    public ResponseEntity<?> updatePassword(@PathVariable Long id, @RequestBody EmployeeLoginDTO password){
        return ResponseEntity.status(HttpStatus.OK).body(service.updatePassWord(id,password.getPassword()));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.MULTI_STATUS).body(service.deleteEmployee(id));
    }
    @Autowired
    private TransactionalService transactionalService;
    @GetMapping("/getEvenEmployee/{id}")
    public ResponseEntity<?> getEvenEmployee(@PathVariable Long id, @RequestBody EmployeeLoginDTO name){
        return ResponseEntity.status(HttpStatus.OK).body(transactionalService.getEvenEmployeeID(id, name.getUsername()));
    }

}
