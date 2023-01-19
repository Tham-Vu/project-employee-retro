package com.example.projectemployeeretro.spring_event;

import org.springframework.context.ApplicationEvent;

import java.time.Clock;

public class CustomEvent extends ApplicationEvent {
    public CustomEvent(Object source) {
        super(source);
    }
}
