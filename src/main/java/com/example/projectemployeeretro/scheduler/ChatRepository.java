package com.example.projectemployeeretro.scheduler;

import com.example.projectemployeeretro.scheduler.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {
}
