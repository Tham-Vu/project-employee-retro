package com.example.projectemployeeretro.service;

import com.example.projectemployeeretro.dto.PositionDTO;
import com.example.projectemployeeretro.entity.Position;
import com.example.projectemployeeretro.repository.PositionRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class PositionServiceImpl implements PositionService{
    @Autowired
    private PositionRepo repo;
    @Autowired
    private ModelMapper mapper;
    @Override
    public List<PositionDTO> getAll() {
        List<Position> list= repo.findAll();
        List<PositionDTO> dtos = new ArrayList<>();
        for (Position p: list) {
            dtos.add(mapper.map(p, PositionDTO.class));
        }
        return dtos;
    }

    @Override
    public Position create(PositionDTO dto) {
        Position position = mapper.map(dto, Position.class);
        return repo.save(position);
    }
}
