package com.example.projectemployeeretro.controller;

import com.example.projectemployeeretro.dto.EmployeeCreationDTO;
import com.example.projectemployeeretro.dto.EmployeeDTO;
import com.example.projectemployeeretro.entity.Employee;
import com.example.projectemployeeretro.entity.Role;
import com.example.projectemployeeretro.jwt.JwtAuthFilter;
import com.example.projectemployeeretro.repository.EmployeeRepository;
import com.example.projectemployeeretro.repository.RoleRepository;
import com.example.projectemployeeretro.service.EmployeeServiceImpl;
import com.example.projectemployeeretro.service.RoleServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {
//    @MockBean
//    private RoleController roleController;
    @MockBean
    private EmployeeRepository repository;

    @Autowired
    private MockMvc mvc;
    @MockBean
    private JwtAuthFilter jwtAuthFilter;
    @MockBean
    private EmployeeServiceImpl service;
    @Autowired
    ModelMapper mapper;

    @Test
    void getAllEmployee() throws Exception {
        List<Employee> list = new ArrayList<>();
        EmployeeCreationDTO dto = new EmployeeCreationDTO("Tham","vu@gmail.com", LocalDate.of(2001, 04, 17),"vutham",1L, "142857",null );
        list.add(mapper.map(dto, Employee.class));
        given(repository.saveAllAndFlush(list)).willReturn(list);

        mvc.perform(get("/employees").contentType(MediaType.APPLICATION_JSON)) // Thực hiện GET REQUEST
                .andExpect(status().isOk()); // Mong muốn Server trả về status 200
//                .andExpect(jsonPath("$", hasSize(10))) // Hi vọng server trả về List độ dài 10
//                .andExpect(jsonPath("$[0].id", is(0))) // Hi vọng phần tử trả về đầu tiên có id = 0
//                .andExpect(jsonPath("$[0].name", is("admin"))); // Hi vọng phần tử trả về đầu tiên có title = "title-0"
    }
}