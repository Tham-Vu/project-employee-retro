package com.example.projectemployeeretro.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

//@Configuration
public class RedisEx implements CommandLineRunner {
    @Autowired
    private RedisTemplate template;
    @Override
    public void run(String... args) throws Exception {
        template.opsForValue().set("loda","hello world");

        // In ra màn hình Giá trị của key "loda" trong Redis
        System.out.println("Value of key loda: "+template.opsForValue().get("loda"));

    }
}
