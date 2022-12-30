package com.example.projectemployeeretro.dto;

import com.example.projectemployeeretro.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO implements Serializable {
    private Long id;
    private String name;
//    private List<Employee> employees;
}
