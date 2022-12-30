package com.example.projectemployeeretro.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class Scheduler {
    private final ChatRepository repository;
    @Scheduled(fixedDelay = 5000)
    public void postMessage(){
        Chat chat = new Chat("Bot", "fixedDelay " + LocalDateTime.now());
        repository.save(chat);
        System.out.println(chat.toString());
    }
    @Scheduled(fixedDelayString = "5000")
    public void postSomething(){
        Chat chat  = new Chat("Bot", "fixedDelayString " + LocalDateTime.now());
        repository.save(chat);
        System.out.println(chat.toString());
    }
    @Scheduled(fixedRate = 5000)
    public void postNothing(){
        Chat chat = new Chat("Bot", "fixedRate " + LocalDateTime.now());
        repository.save(chat);
        System.out.println(chat.toString());
    }
    @Scheduled(fixedRateString = "5000")
    public void postAnyThing(){
        Chat chat = new Chat("Bot","fixedRateString " + LocalDateTime.now());
        repository.save(chat);
        System.out.println(chat.toString());
    }
    @Scheduled(initialDelay = 5000, fixedRate = 5000)
    public void noPost(){
        Chat chat = new Chat("Me", "initialDelay " + LocalDateTime.now());
        repository.save(chat);
        System.out.println(chat.toString());
    }
    @Scheduled(initialDelayString = "5000", fixedDelay = 5000)
    public void postInitial(){
        Chat chat = new Chat("Me", "initialDelayString " + LocalDateTime.now());
        repository.save(chat);
        System.out.println(chat.toString());
    }
    @Scheduled(zone = "5000", fixedDelay = 5000)
    public void postZone(){
        Chat chat = new Chat("Me", "zone " + LocalDateTime.now());
        repository.save(chat);
        System.out.println(chat.toString());
    }
    @Scheduled(cron = "5 * * * * ?")
    public void postHaveLunch(){
        Chat chat = new Chat("Bot", "cron " + LocalDateTime.now());
        repository.save(chat);
        System.out.println(chat.toString());
    }
}
