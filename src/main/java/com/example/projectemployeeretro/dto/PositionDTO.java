package com.example.projectemployeeretro.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PositionDTO implements Serializable {
    private Long id;
    private String name;

    public PositionDTO(String name) {
        this.name = name;
    }
}
