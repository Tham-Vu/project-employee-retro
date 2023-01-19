package com.example.projectemployeeretro.scheduler.schedule_config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleTimeService {
    @Autowired
    private ScheduleTimeConfigRepo repo;
    public ScheduleTimeEntity createCustomTime(ScheduleTimeEntity value){
        ScheduleTimeEntity exist = repo.findByValue(value.getValue());
        if (exist != null){
            return exist;
        }else return repo.save(value);
    }
    public List<ScheduleTimeEntity> getAll(){
        return repo.findAll();
    }
    public ScheduleTimeEntity findByValue(ScheduleTimeEntity value){
        return repo.findByValue(value.getValue());
    }
}
