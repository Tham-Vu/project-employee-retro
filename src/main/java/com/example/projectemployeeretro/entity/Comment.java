package com.example.projectemployeeretro.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
//    @EmbeddedId
//    EmployeeProjectKey id;
    @Id
    private Long id;
    @ManyToOne
    @MapsId("employee_id")
    private Employee employee;

    @ManyToOne
    @MapsId("project_id")
    private  Project project;
    private String content;
    private LocalDate date;
}
