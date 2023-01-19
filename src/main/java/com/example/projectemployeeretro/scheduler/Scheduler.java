package com.example.projectemployeeretro.scheduler;

import com.example.projectemployeeretro.scheduler.schedule_config.ScheduleTimeConfigRepo;
import com.example.projectemployeeretro.scheduler.schedule_config.ScheduleTimeEntity;
import com.example.projectemployeeretro.scheduler.schedule_config.ScheduleTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class Scheduler{
    private final ChatRepository repository;
    @Autowired
    ScheduleTimeConfigRepo repo;
//    @Scheduled(fixedDelay = 5000)
    public void postMessage(){
        Chat chat = new Chat("Bot", "fixedDelay " + LocalDateTime.now());
        repository.save(chat);
        System.out.println(chat.toString());
    }
//    @Scheduled(fixedDelayString = "5000")
    public void postSomething(){
        Chat chat  = new Chat("Bot", "fixedDelayString " + LocalDateTime.now());
        repository.save(chat);
        System.out.println(chat.toString());
    }
//    @Scheduled(fixedRate = 5000)
    public void postNothing(){
        Chat chat = new Chat("Bot", "fixedRate " + LocalDateTime.now());
        repository.save(chat);
        System.out.println(chat.toString());
    }
//    @Scheduled(fixedRateString = "5000")
    public void postAnyThing(){
        Chat chat = new Chat("Bot","fixedRateString " + LocalDateTime.now());
        repository.save(chat);
        System.out.println(chat.toString());
    }
//    @Scheduled(initialDelay = 5000, fixedRate = 5000)
    public void noPost(){
        Chat chat = new Chat("Me", "initialDelay " + LocalDateTime.now());
        repository.save(chat);
        System.out.println(chat.toString());
    }
//    @Scheduled(initialDelayString = "5000", fixedDelay = 5000)
    public void postInitial(){
        Chat chat = new Chat("Me", "initialDelayString " + LocalDateTime.now());
        repository.save(chat);
        System.out.println(chat.toString());
    }
//    @Scheduled(zone = "5000", fixedDelay = 5000)
    public void postZone(){
        Chat chat = new Chat("Me", "zone " + LocalDateTime.now());
        System.out.println(chat.toString());
    }
    @Scheduled(cron = "0-30 * * * * ?")
    public String getCronFromDatabase(){
        String crond = repo.findAll().get(repo.findAll().size()-1).getValue();
        System.out.println(crond + LocalDateTime.now());
        return crond;
    }

    public void postHaveLunch(){
//        Chat chat = new Chat("Bot", "cron " + LocalDateTime.now());
//        System.out.println(chat.getSender() + "++++" + chat.getMessage());
        System.out.println("Bot: ++++++++++++++++ cron " + LocalDateTime.now());
    }
}
