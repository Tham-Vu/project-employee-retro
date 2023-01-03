package com.example.projectemployeeretro.service;

import com.example.projectemployeeretro.dto.RoleDTO;
import com.example.projectemployeeretro.entity.Role;
import com.example.projectemployeeretro.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ModelMapper mapper;

    public RoleDTO conversionToDto(Long id){
        Role role = roleRepository.findById(id).orElse(null);
        RoleDTO roleDTO = mapper.map(role, RoleDTO.class);
        return roleDTO;
    }
    public Role conversionToEntity(RoleDTO roleDTO){
        Role role = mapper.map(roleDTO, Role.class);
        return role;
    }
    @Override
    public List<Role> getAllRole(){
        return roleRepository.findAll();
    }
    @Override
    public ResponseEntity<?> saveRole(RoleDTO dto){
        Role role = conversionToEntity(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(roleRepository.save(role));
    }

    @Override
    public Role saveRole(Role dto) {
//        Role role = conversionToEntity(dto);
        return roleRepository.save(dto);
    }

    @Override
    public ResponseEntity<?> updateRole(RoleDTO dto, Long id){
        Role updateRole = roleRepository.findById(id).map(role -> {
            role.setName(dto.getName());
            return roleRepository.save(role);
        }).orElseGet(()->{
            dto.setId(id);
            return roleRepository.save(conversionToEntity(dto));
        });
        return ResponseEntity.status(HttpStatus.RESET_CONTENT).body(roleRepository.save(updateRole));
    }
    @Override
    public ResponseEntity<?> deleteRole(Long id){
        boolean isExist = roleRepository.existsById(id);
        if(!isExist){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        }else {
            roleRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Delete Successfully");
        }
    }
}
