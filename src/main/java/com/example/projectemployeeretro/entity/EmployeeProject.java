package com.example.projectemployeeretro.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode
public class EmployeeProject {
    @EmbeddedId
    private EmployeeProjectKey id;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    @MapsId("employeeId")
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @JsonIgnore
    @ManyToOne
    @MapsId("projectId")
    @JoinColumn(name = "project_id")
    private Project project;
    @OneToMany
    private List<Position> position;
}
