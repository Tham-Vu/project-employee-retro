package com.example.projectemployeeretro.config;

import com.example.projectemployeeretro.service.RoleService;
import com.example.projectemployeeretro.service.RoleServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ConfigTest {
    @Bean
    public RoleService roleService(){
        return new RoleServiceImpl();
    }
    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }
}
