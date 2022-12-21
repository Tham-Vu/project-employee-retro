package com.example.projectemployeeretro.controller;

import com.example.projectemployeeretro.dto.ProjectDTO;
import com.example.projectemployeeretro.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @GetMapping
    public ResponseEntity<?> getAllProject(){
        return ResponseEntity.status(HttpStatus.OK).body(projectService.getAllProject());
    }
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getProjectById(@PathVariable Long id){
//        Optional<Project> project = repository.findById(id);
//        if (project.isPresent()) {
//            return ResponseEntity.status(HttpStatus.OK).body(project);
//        }else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cannot find employee with id " + id);
//    }
    @PostMapping("/insert")
    public ResponseEntity<?> insertProject(@RequestBody ProjectDTO project){
        return ResponseEntity.status(HttpStatus.OK).body(projectService.saveProject(project));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProject(@RequestBody ProjectDTO projectDTO, @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(projectService.updateProject(projectDTO, id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.MULTI_STATUS).body(projectService.deleteProject(id));
    }
}
