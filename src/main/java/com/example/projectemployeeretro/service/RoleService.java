package com.example.projectemployeeretro.service;

import com.example.projectemployeeretro.dto.RoleDTO;
import com.example.projectemployeeretro.entity.Role;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoleService {
    List<RoleDTO> getAllRole();

    ResponseEntity<?> saveRole(RoleDTO dto);
    Role saveRole(Role dto);

    ResponseEntity<?> updateRole(RoleDTO dto, Long id);

    ResponseEntity<?> deleteRole(Long id);
}
