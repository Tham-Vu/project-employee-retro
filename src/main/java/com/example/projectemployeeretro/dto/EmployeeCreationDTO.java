package com.example.projectemployeeretro.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeCreationDTO implements Serializable {
    private String fullName;
    private String email;
    private LocalDate birthday;
    private String username;
    private Long role_id;
    private String password;
    private Set<Long> projectId;
}
