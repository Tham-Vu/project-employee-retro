package com.example.projectemployeeretro.dto;

import com.example.projectemployeeretro.entity.Project;
import com.example.projectemployeeretro.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeDTO implements Serializable {
//    private Long id;
    private String fullName;
    private String email;
    private LocalDate birthDay;
    private String userName;
    private Role user_role;
    private List<Project> projects;
}
