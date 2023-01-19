package com.example.projectemployeeretro.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@Configuration
//public class WebConfig implements WebApplicationInitializer{
public class WebConfig{
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return new ModelMapper();
    }

//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        servletContext.setInitParameter("spring.profiles.active", "dev");
//    }
}
