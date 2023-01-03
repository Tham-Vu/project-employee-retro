package com.example.projectemployeeretro;

import com.example.projectemployeeretro.entity.Employee;
import com.example.projectemployeeretro.repository.EmployeeRepository;
import com.example.projectemployeeretro.service.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
//@Import(Config.class)
public class TestConfig {
    @TestConfiguration
    public static class Config {
        @Bean
        public BasicTest basicTest(){
            return new BasicTest();
        }
    }

    @Autowired
    private BasicTest basicTest;
    @Test
    public void setBasicTest() {
        int a = 20;
        int b = 50;
        int result = basicTest.add(a, b);
        assertThat(result).isEqualTo(70);
    }

}
