package com.example.projectemployeeretro.spring_event;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class CustomListener {
    @EventListener
    @Async
    public void listenEvent(CustomEvent event) throws InterruptedException {
        Thread.sleep(5000);
        System.out.println(Thread.currentThread().getName() + ": Active");
        System.out.println(String.format("%s: Xảy ra sự kiện %s!!!", Thread.currentThread().getName(), event.getSource()));
    }
}
