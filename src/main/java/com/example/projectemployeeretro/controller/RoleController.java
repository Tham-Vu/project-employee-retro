package com.example.projectemployeeretro.controller;

import com.example.projectemployeeretro.dto.RoleDTO;
import com.example.projectemployeeretro.repository.RoleRepository;
import com.example.projectemployeeretro.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @GetMapping
    public ResponseEntity<?> getAllRole(){
        return ResponseEntity.status(HttpStatus.OK).body(roleService.getAllRole());
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insertRole(@RequestBody RoleDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(roleService.saveRole(dto));
    }
    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateRole(@RequestBody RoleDTO dto, @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.RESET_CONTENT).body(roleService.updateRole(dto, id));
    }
    @PutMapping("/updatePut/{id}")
    public ResponseEntity<?> updatePutRole(@RequestBody RoleDTO dto, @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.RESET_CONTENT).body(roleService.updateRole(dto, id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.MULTI_STATUS).body(roleService.deleteRole(id));
    }
}
