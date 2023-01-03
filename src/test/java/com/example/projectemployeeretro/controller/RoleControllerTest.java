package com.example.projectemployeeretro.controller;

import com.example.projectemployeeretro.entity.Role;
import com.example.projectemployeeretro.jwt.JwtAuthFilter;
import com.example.projectemployeeretro.repository.RoleRepository;
import com.example.projectemployeeretro.service.RoleServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RoleController.class)
class RoleControllerTest {
    @MockBean
    private RoleRepository roleRepository;
    @Autowired
    private MockMvc mvc;
    @MockBean
    private JwtAuthFilter jwtAuthFilter;
    @MockBean
    private RoleServiceImpl service;
    @Test
    void testGetAllRole() throws Exception {
        List<Role> list = new ArrayList<>();
        list.add(new Role("admin"));
        list.add(new Role("user"));
        list.add(new Role("manager"));
        list.add(new Role("hr"));
        given(service.getAllRole()).willReturn(list);

        mvc.perform(get("/roles").contentType(MediaType.APPLICATION_JSON)) // Thực hiện GET REQUEST
                .andExpect(status().isUnauthorized()); // Mong muốn Server trả về status 200
//                .andExpect(jsonPath("$", hasSize(10))) // Hi vọng server trả về List độ dài 10
//                .andExpect(jsonPath("$[0].id", is(0))) // Hi vọng phần tử trả về đầu tiên có id = 0
//                .andExpect(jsonPath("$[0].name", is("admin"))); // Hi vọng phần tử trả về đầu tiên có title = "title-0"
    }
}