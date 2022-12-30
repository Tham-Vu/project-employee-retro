package com.example.projectemployeeretro.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
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
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
//    @JoinColumn(nullable = false)
    private Role role;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "employee_project",
            joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"))
    private Set<Project> projects;
//    @Transient
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Role role = this.getUser_role();
//        return Collections.singleton(new SimpleGrantedAuthority(role.getRoleName())) ;
//    }


    public Employee(Long id) {
        this.id = id;
    }
}
