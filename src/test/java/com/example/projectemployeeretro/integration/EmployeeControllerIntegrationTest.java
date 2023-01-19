package com.example.projectemployeeretro.integration;

import com.example.projectemployeeretro.ProjectEmployeeRetroApplication;
import com.example.projectemployeeretro.controller.EmployeeController;
import com.example.projectemployeeretro.dto.EmployeeDTO;
import com.example.projectemployeeretro.entity.Employee;
import com.example.projectemployeeretro.entity.Role;
import com.example.projectemployeeretro.repository.EmployeeRepository;
import com.example.projectemployeeretro.service.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerIntegrationTest {
    @Autowired
    ModelMapper mapper;
    @Autowired
    private MockMvc mvc;
    @MockBean
    private EmployeeServiceImpl repository;
    @Test
    public void givenEmployees_whenGetEmployee_thenStatus200()throws Exception{
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFullName("Tham");
        employee.setUsername("vutham");
        employee.setPassword("142857");
        employee.setRole(new Role(1l,"admin",null ));
        employee.setEmail("mahesh@test.com");
        given(repository.getAllEmployee()).willReturn((List<EmployeeDTO>) employee);

        mvc.perform(get("/employees").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk()) // Mong muốn Server trả về status 200
                .andExpect(jsonPath("$[0].id", is(1)));
    }
}
