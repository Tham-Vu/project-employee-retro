package com.example.projectemployeeretro.controller;

import com.example.projectemployeeretro.entity.Employee;
import com.example.projectemployeeretro.entity.Project;
import com.example.projectemployeeretro.repository.EmployeeRepository;
import com.example.projectemployeeretro.repository.ProjectRepository;
import com.example.projectemployeeretro.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ProjectRepository repository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @GetMapping
    public ResponseEntity<?> getAllProject(){
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getProjectById(@PathVariable Long id){
        Optional<Project> project = repository.findById(id);
        if (project.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(project);
        }else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cannot find employee with id " + id);
    }
    @PostMapping("/insert")
    public ResponseEntity<?> insertProject(@RequestBody Project project){
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(project));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProject(@RequestBody Project newProject, @PathVariable Long id){
        Project updateProject = repository.findById(id)
                .map(project -> {
                    project.setProjectName(newProject.getProjectName());
                    project.setEndDate(newProject.getEndDate());
                    project.setStartDate(newProject.getStartDate());
                    return repository.save(project);
                }).orElseGet(() -> {
                    newProject.setId(id);
                    return repository.save(newProject);
                });
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(updateProject));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Long id){
        boolean exists = repository.existsById(id);
        if (exists){
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Delete employee with id " + id + " successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Cannot delete");
    }
}
