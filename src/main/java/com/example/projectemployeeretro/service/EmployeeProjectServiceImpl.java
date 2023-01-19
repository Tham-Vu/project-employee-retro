package com.example.projectemployeeretro.service;

import com.example.projectemployeeretro.dto.EmployeeProjectCreationDTO;
import com.example.projectemployeeretro.dto.EmployeeProjectDTO;
import com.example.projectemployeeretro.entity.EmployeeProject;
import com.example.projectemployeeretro.entity.EmployeeProjectKey;
import com.example.projectemployeeretro.entity.Position;
import com.example.projectemployeeretro.repository.EmployeeProjectRepo;
import com.example.projectemployeeretro.repository.EmployeeRepository;
import com.example.projectemployeeretro.repository.PositionRepo;
import com.example.projectemployeeretro.repository.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeProjectServiceImpl implements EmployeeProjectService{
    @Autowired
    private EmployeeProjectRepo repo;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private PositionRepo positionRepo;
    @Override
    public List<EmployeeProjectDTO> getAll() {
        return repo.findAll().stream().map(e -> mapper.map(e, EmployeeProjectDTO.class)).collect(Collectors.toList());
    }

    @Override
    public EmployeeProjectDTO create(EmployeeProjectCreationDTO dto) {
        EmployeeProject employeeProject = new EmployeeProject();
        employeeProject.setId(new EmployeeProjectKey(dto.getEmployee_id(), dto.getProject_id()));
        employeeProject.setEmployee(employeeRepository.findById(dto.getEmployee_id()).get());
        employeeProject.setProject(projectRepository.findById(dto.getProject_id()).get());
        employeeProject.setPosition(positionRepo.findAllById(dto.getPosition_id()));
        repo.save(employeeProject);
        System.out.println(employeeProject);
        return mapper.map(employeeProject, EmployeeProjectDTO.class);
    }
}
