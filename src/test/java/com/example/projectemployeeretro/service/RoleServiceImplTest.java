package com.example.projectemployeeretro.service;

import com.example.projectemployeeretro.entity.Role;
import com.example.projectemployeeretro.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class RoleServiceImplTest {
//    @TestConfiguration
//    static class RoleServiceImplTestConfig{
//        @Bean
//        public RoleService roleService(){
//            return new RoleServiceImpl();
//        }
//        @Bean
//        public ModelMapper mapper(){
//            return new ModelMapper();
//        }
//    }
    @MockBean
    private ModelMapper mapper;
    @InjectMocks
    private RoleServiceImpl service;
    @MockBean
    RoleRepository roleRepository;

    @Test
    void testGetAllRole() {
        Role role = new Role();
        role.setName("manager");
        List<Role> list = new ArrayList<>();
        list.add(role);
        when(roleRepository.findAll()).thenReturn(list);
        List<Role> result = service.getAllRole();
        assertEquals(list, result);
    }
    @Test
    void testSaveRole(){
        Role role = new Role();
        role.setName("manager");
        when(roleRepository.save(role)).thenReturn(role);
        assertEquals(service.saveRole(role), role);
    }
}