package com.example.projectemployeeretro.integration;

import com.example.projectemployeeretro.ProjectEmployeeRetroApplication;
import com.example.projectemployeeretro.entity.Employee;
import com.example.projectemployeeretro.entity.Role;
import com.example.projectemployeeretro.repository.EmployeeRepository;
import com.example.projectemployeeretro.service.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

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

@SpringBootTest(                                            //tạo ApplicationContext trong môi trường Test để nap các bean
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,//cấu hình môi trường runtime, *.Mock: container sẽ hoạt động trong môi trường servlet giả
        classes  = ProjectEmployeeRetroApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties"//cấu hình vị trí file chứa các cấu hình dành cho môi trường test Lưu ý rằng tệp thuộc tính được tải bằng @TestPropertySource sẽ ghi đè lên tệp application.properties hiện có .
)
public class EmployeeControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private EmployeeServiceImpl repository;
    private Employee inputEmployee() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFullName("Tham");
        employee.setUsername("vutham");
        employee.setPassword("142857");
        employee.setRole(new Role(1l,"admin",null ));
        employee.setEmail("mahesh@test.com");
        return employee;
    }
    @Test
    public void givenEmployees_whenGetEmployee_thenStatus200()throws Exception{
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFullName("Tham");
        employee.setUsername("vutham");
        employee.setPassword("142857");
        employee.setRole(new Role(1l,"admin",null ));
        employee.setEmail("mahesh@test.com");
        given(repository.getAllEmployee()).willReturn(Collections.singletonList(employee));

        mvc.perform(get("/employees").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk()) // Mong muốn Server trả về status 200
                .andExpect(jsonPath("$[0].id", is(1)));
    }
}
