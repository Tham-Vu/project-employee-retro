package com.example.projectemployeeretro.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment implements Serializable {
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
