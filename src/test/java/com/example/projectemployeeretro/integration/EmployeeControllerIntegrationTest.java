package com.example.projectemployeeretro.integration;

import com.example.projectemployeeretro.ProjectEmployeeRetroApplication;
import com.example.projectemployeeretro.entity.Employee;
import com.example.projectemployeeretro.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
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
    @Autowired
    private EmployeeRepository repository;
    private Employee inputEmployee() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFullName("Tham");
        employee.setUsername("vutham");
        employee.setEmail("mahesh@test.com");
        return employee;
    }
    @Test
    public void givenEmployees_whenGetEmployee_thenStatus200()throws Exception{
        inputEmployee();

        mvc.perform(get("/api/employees").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].username", is("vutham")));
    }
}
