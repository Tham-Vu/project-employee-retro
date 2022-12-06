package com.example.projectemployeeretro.service;

import com.example.projectemployeeretro.entity.Employee;
import com.example.projectemployeeretro.entity.Project;
import com.example.projectemployeeretro.repository.EmployeeRepository;
import com.example.projectemployeeretro.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public ResponseEntity<?> insertProject(Project project){
        Project insertProject = new Project();
        insertProject.setProjectName(project.getProjectName());
        insertProject.setStartDate(project.getStartDate());
        insertProject.setEndDate(project.getEndDate());
        List<Project> projectList = new ArrayList<>();
        projectList.add(insertProject);
        for (int i = 0; i < project.getEmployees().size(); i++) {
            if (!employeeRepository.findByEmail(project.getEmployees().get(i).getEmail()).isPresent()){
                Employee newEmployee = project.getEmployees().get(i);
                newEmployee.setProjects(projectList);
                Employee saveEmployee = employeeRepository.save(new Employee());
                if (!employeeRepository.findById(saveEmployee.getId()).isPresent())
                    return ResponseEntity.unprocessableEntity().body("Project creation failed");
            }else
                return ResponseEntity.unprocessableEntity().body("Employee with email id is already present");
        }
        return ResponseEntity.ok("Successfully created project");
    }
}
