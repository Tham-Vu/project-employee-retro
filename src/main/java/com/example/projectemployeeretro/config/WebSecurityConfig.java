package com.example.projectemployeeretro.config;

import com.example.projectemployeeretro.jwt.JwtAuthenticationEntryPoint;
import com.example.projectemployeeretro.jwt.JwtUtils;
import com.example.projectemployeeretro.jwt.filter.JwtAuthenticationFilter;
import com.example.projectemployeeretro.jwt.filter.JwtAuthorizationFilter;
import com.example.projectemployeeretro.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class WebSecurityConfig{
    @Autowired
    private JwtAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    protected UserDetailsService userDetailsService(){
        return new CustomUserDetailsService();
    }
    @Bean
    public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }
    @Bean
    protected SecurityFilterChain configuration(HttpSecurity http) throws Exception{
//        http.cors().and().csrf().disable().authorizeHttpRequests().anyRequest().permitAll();
        JwtAuthenticationFilter authenticationFilter = new JwtAuthenticationFilter(authenticationManagerBean(http));
        authenticationFilter.setFilterProcessesUrl("/api/login");
        http.cors().and().csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/employees", "/employees/hello", "/employees/insert", "/api/login").permitAll()
                .antMatchers("/employees/**").permitAll()
                .antMatchers("/roles","/employees/home").hasAuthority("admin")
                .anyRequest().authenticated();
        http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilter(authenticationFilter);
//                .and().httpBasic();
        return http.build();
    }

}
