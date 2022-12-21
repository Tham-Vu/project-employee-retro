package com.example.projectemployeeretro.service;

import com.example.projectemployeeretro.entity.CustomUserDetails;
import com.example.projectemployeeretro.entity.Employee;
import com.example.projectemployeeretro.entity.Role;
import com.example.projectemployeeretro.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private EmployeeRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = repository.findEmployeeByUserName(username);
        if (employee == null){
            System.out.println("Employee not found! " + username);
            throw new UsernameNotFoundException("Employee " + username + "was not found in the database");
        }else {
            System.out.println("Found employee!" + username);
        }
        return new CustomUserDetails(employee);
    }
}
