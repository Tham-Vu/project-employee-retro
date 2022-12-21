package com.example.projectemployeeretro.repository;

import com.example.projectemployeeretro.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
   Employee findEmployeeByUserName(String userName);
}
