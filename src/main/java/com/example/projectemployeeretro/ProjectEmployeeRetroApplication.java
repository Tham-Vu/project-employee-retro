package com.example.projectemployeeretro;

import com.example.projectemployeeretro.entity.Employee;
import com.example.projectemployeeretro.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@SpringBootApplication
//@EnableScheduling
public class ProjectEmployeeRetroApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(ProjectEmployeeRetroApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
	}
}
