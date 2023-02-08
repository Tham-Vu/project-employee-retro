package com.example.projectemployeeretro.scheduler.schedule_config;

import com.example.projectemployeeretro.scheduler.Scheduler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.security.core.parameters.P;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.stream.Collectors;

@Configuration
@EnableScheduling
@Slf4j
public class SpringSchedulingConfig implements SchedulingConfigurer {
    @Autowired
    private Scheduler scheduler;
    @Autowired
    private ScheduleTimeService service;
    private String crond;
    private ScheduledFuture scheduledFuture;

    @Bean
    public TaskScheduler taskScheduler(){
        ThreadPoolTaskScheduler threadPool = new ThreadPoolTaskScheduler();
        threadPool.setPoolSize(1);
        threadPool.setThreadNamePrefix("ThreadPoolScheduler");
        threadPool.initialize();
        return threadPool;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskScheduler());
        scheduleFuture(taskScheduler());
    }

    public void refreshCronSchedule(){
        scheduledFuture.cancel(true);
        scheduleFuture(taskScheduler());
    }
    private void scheduleFuture(TaskScheduler taskScheduler){
        scheduledFuture = taskScheduler().schedule(() -> scheduler.postHaveLunch(), new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                crond = scheduler.getCronFromDatabase();
                return new CronTrigger(crond).nextExecutionTime(triggerContext);
            }
        });
    }
}
