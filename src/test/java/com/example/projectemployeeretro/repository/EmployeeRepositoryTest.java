package com.example.projectemployeeretro.repository;

import com.example.projectemployeeretro.ProjectEmployeeRetroApplication;
import com.example.projectemployeeretro.dto.EmployeeCreationDTO;
import com.example.projectemployeeretro.entity.Employee;
import com.example.projectemployeeretro.entity.Project;
import com.example.projectemployeeretro.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


//@SpringBootTest
//@TestPropertySource(
//        locations = "s/application.properties"//cấu hình vị trí file chứa các cấu hình dành cho môi trường test Lưu ý rằng tệp thuộc tính được tải bằng @TestPropertySource sẽ ghi đè lên tệp application.properties hiện có .
//)
@DataJpaTest
class EmployeeRepositoryTest {
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
    void getEmployee() {
    }

    @Test
    void findAllEmployeeWithPagination() {
    }

    @Test
    void findAllEmployeeWithSlice() {
    }
    @Test
    public void testSave() {
        Employee employee = inputEmployee();
        repository.save(employee);
        Employee found = repository.findById(employee.getId()).get();
        assertThat(employee.getId(),equalTo(found.getId()));
    }
    @Test
    void findEmployeeByUsername() {
        Employee employee = inputEmployee();
        repository.save(employee);
        Employee results = repository.findEmployeeByUsername("vutham");
        assertThat(employee.getUsername(),equalTo(results.getUsername()));
    }

    @Test
    void getAllEmail() {
    }

    @Test
    void countTotalEmployeeByRole() {
    }

    @Test
    void countTotalEmployeeByRoleInterface() {
    }

    @Test
    void getEmployeeByRoleName() {
    }
}