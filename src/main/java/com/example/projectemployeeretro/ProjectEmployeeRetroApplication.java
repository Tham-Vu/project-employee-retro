package com.example.projectemployeeretro;

import com.example.projectemployeeretro.entity.Employee;
import com.example.projectemployeeretro.entity.Project;
import com.example.projectemployeeretro.repository.EmployeeRepository;
import com.example.projectemployeeretro.repository.ProjectRepository;
import com.example.projectemployeeretro.service.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
public class ProjectEmployeeRetroApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectEmployeeRetroApplication.class, args);
	}
//	@Bean
//	public CommandLineRunner mappingDemo(EmployeeRepository employeeRepository, ProjectRepository projectRepository){
//		return  args -> {
//			Employee employee = new Employee();
//			employee.setFullName("Vu");
//			employee.setEmail("vu@gmail.com");
//			employee.setBirthDay(LocalDate.of(2001,04,17));
//			employee.setUser_role("USER");
//			employeeRepository.save(employee);
//			Project project = new Project("java training",LocalDate.of(2022,11,07), LocalDate.of(2022,01,07));
//			Project project1 = new Project("spring training",LocalDate.of(2022,01,07), LocalDate.of(2022,07,07));
//			projectRepository.saveAll(Arrays.asList(project, project1));
//			employee.getProjects().addAll(Arrays.asList(project, project1));
//			employeeRepository.save(employee);
//
//		};
//	}

}
