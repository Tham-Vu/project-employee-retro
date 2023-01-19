package com.example.projectemployeeretro.repository;

import com.example.projectemployeeretro.entity.EmployeeProject;
import com.example.projectemployeeretro.entity.EmployeeProjectKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeProjectRepo extends JpaRepository<EmployeeProject, EmployeeProjectKey> {
    List<EmployeeProject> findByIdEmployeeId(Long id);
    List<EmployeeProject> findByIdProjectId(Long id);
}
