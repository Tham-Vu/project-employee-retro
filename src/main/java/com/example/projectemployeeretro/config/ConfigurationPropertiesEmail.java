package com.example.projectemployeeretro.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "my-email")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@PropertySource("classpath:application-dev.properties")
public class ConfigurationPropertiesEmail {
    private String username;
    private String password;
}
