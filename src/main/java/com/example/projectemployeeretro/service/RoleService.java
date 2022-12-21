package com.example.projectemployeeretro.service;

import com.example.projectemployeeretro.dto.RoleDTO;
import org.springframework.http.ResponseEntity;

public interface RoleService {
    ResponseEntity<?> getAllRole();

    ResponseEntity<?> saveRole(RoleDTO dto);

    ResponseEntity<?> updateRole(RoleDTO dto, Long id);

    ResponseEntity<?> deleteRole(Long id);
}
