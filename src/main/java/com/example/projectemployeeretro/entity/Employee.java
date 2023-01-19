package com.example.projectemployeeretro.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.modelmapper.spi.SourceGetter;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String email;
    private LocalDate birthday;
    private String username;
    private String password;
    @ManyToOne(cascade = CascadeType.ALL)
    private Role role;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private Set<EmployeeProject> employeeProjects;

    public Employee(Long id) {
        this.id = id;
    }

    public Employee(String username) {
        this.username = username;
    }
}
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "employee_project2",
//            joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"))
//    private Set<Project> projects;