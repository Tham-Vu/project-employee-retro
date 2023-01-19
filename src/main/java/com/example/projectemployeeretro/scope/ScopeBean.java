package com.example.projectemployeeretro.scope;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDateTime;

@Component
@Scope("prototype")
public class ScopeBean {
    @PostConstruct
    public void postConstruct(){
        System.out.println("Post Construct");
    }
    @PreDestroy
    public void preDestroy(){
        System.out.println("Pre destroy");
    }
}
