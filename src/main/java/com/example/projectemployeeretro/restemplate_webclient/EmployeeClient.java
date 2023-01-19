package com.example.projectemployeeretro.restemplate_webclient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeClient {
    private String firstName;
    private String lastName;
    private String email;
}
