package com.example.projectemployeeretro.spring_event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.Clock;

@Component
public class CustomPublisher {
    @Autowired
    private ApplicationEventPublisher publisher;

    public void doEvent(String name){
        publisher.publishEvent(new CustomEvent(name));
    }
}
