package com.example.projectemployeeretro.scheduler.schedule_config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScheduleConfigController {
    @Autowired
    private ScheduleTimeService service;
    @PostMapping("/scheduler/create")
    public ResponseEntity createNewScheduleTime(@RequestBody ScheduleTimeEntity value){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createCustomTime(value));
    }
}
