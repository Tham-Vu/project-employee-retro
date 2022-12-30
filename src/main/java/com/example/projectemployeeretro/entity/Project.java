package com.example.projectemployeeretro.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToMany( cascade = CascadeType.ALL, mappedBy = "projects")
//    @JoinTable(name = "employee_project",
//            inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
//            joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"))
    private Set<Employee> employees;
}
