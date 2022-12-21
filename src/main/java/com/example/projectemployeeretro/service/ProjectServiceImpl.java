package com.example.projectemployeeretro.service;

import com.example.projectemployeeretro.dto.ProjectDTO;
import com.example.projectemployeeretro.entity.Employee;
import com.example.projectemployeeretro.entity.Project;
import com.example.projectemployeeretro.repository.EmployeeRepository;
import com.example.projectemployeeretro.repository.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Slf4j
public class ProjectServiceImpl implements ProjectService{
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper mapper;
    public ProjectDTO conversionToDto(Long id){
        Project project = projectRepository.findById(id).orElse(null);
        ProjectDTO projectDTO = mapper.map(project, ProjectDTO.class);
        return projectDTO;
    }
    public Project conversionToEntity(ProjectDTO projectDTO){
        Project project = mapper.map(projectDTO, Project.class);
        return project;
    }

    @Override
    public ResponseEntity<?> getAllProject(){
        return ResponseEntity.status(HttpStatus.OK).body(projectRepository.findAll());
    }
    @Override
    public ResponseEntity<?> saveProject(ProjectDTO dto){
        Project project = conversionToEntity(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(projectRepository.save(project));
    }
    @Override
    public ResponseEntity<?> updateProject(ProjectDTO dto, Long id){
        Project updateProject = projectRepository.findById(id)
                .map(project -> {
                    project.setProjectName(dto.getProjectName());
                    project.setStartDate(dto.getStartDate());
                    project.setEndDate(dto.getEndDate());
                    project.setEmployees((Set<Employee>) employeeRepository.findAllById(dto.getEmployeeIds()));
                    return projectRepository.save(project);
                }).orElseGet(()->{
                    conversionToEntity(dto).setId(id);
                    return projectRepository.save(conversionToEntity(dto));
                });
        return ResponseEntity.status(HttpStatus.OK).body(projectRepository.save(updateProject));
    }
    @Override
    public ResponseEntity<?> deleteProject(Long id){
        boolean isExist = projectRepository.existsById(id);
        if(!isExist){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        }else {
            projectRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Delete Successfully");
        }
    }}
