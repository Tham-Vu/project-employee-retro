package com.example.projectemployeeretro.service;

import com.example.projectemployeeretro.dto.ProjectDTO;
import org.springframework.http.ResponseEntity;

public interface ProjectService {
    ResponseEntity<?> getAllProject();
    ResponseEntity<?> getProjectById(Long id);

    ResponseEntity<?> saveProject(ProjectDTO dto);

    ResponseEntity<?> updateProject(ProjectDTO dto, Long id);

    ResponseEntity<?> deleteProject(Long id);
}
