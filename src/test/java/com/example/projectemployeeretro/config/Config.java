package com.example.projectemployeeretro.config;
import com.example.projectemployeeretro.BasicTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class Config {
    @Bean
    public BasicTest basicTest(){
        return new BasicTest();
    }
}
