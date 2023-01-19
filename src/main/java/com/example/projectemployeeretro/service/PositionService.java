package com.example.projectemployeeretro.service;

import com.example.projectemployeeretro.dto.PositionDTO;
import com.example.projectemployeeretro.entity.Position;

import java.util.List;

public interface PositionService {
    List<PositionDTO> getAll();
    Position create(PositionDTO dto);

}
