package com.example.projectemployeeretro.scheduler.schedule_config;

import com.example.projectemployeeretro.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScheduleTimeConfigRepo extends JpaRepository<ScheduleTimeEntity, Long> {
    ScheduleTimeEntity findByValue(String value);
}
