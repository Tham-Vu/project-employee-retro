package com.example.projectemployeeretro.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String projectName;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToMany(targetEntity = Employee.class, mappedBy = "projects", cascade = CascadeType.ALL)
    private List<Employee> employees;

    public Project(String projectName, LocalDate startDate, LocalDate endDate) {
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
