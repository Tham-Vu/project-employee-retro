package com.example.projectemployeeretro.scheduler;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Chat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String sender;
    private String message;

    public Chat(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }
}
