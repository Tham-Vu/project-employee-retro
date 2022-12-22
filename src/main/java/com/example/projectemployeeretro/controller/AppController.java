package com.example.projectemployeeretro.controller;

import com.example.projectemployeeretro.dto.EmployeeLoginDTO;
import com.example.projectemployeeretro.entity.CustomUserDetails;
import com.example.projectemployeeretro.jwt.JwtUtils;
import com.example.projectemployeeretro.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class AppController {
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtils jwtUtils;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody EmployeeLoginDTO dto) throws Exception{
        authenticate(dto.getUserName(), dto.getPassword());
        System.out.println("OK");
        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(dto.getUserName());
        System.out.println(userDetails.getUsername());
        final String token = jwtUtils.generateToken(userDetails);
        log.info(token);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    private void authenticate(String username, String password) throws Exception{
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            System.out.println(new UsernamePasswordAuthenticationToken(username, password));
        }catch (DisabledException e){
            throw new Exception("USER_DISABLE", e);
        }catch (BadCredentialsException e){
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
