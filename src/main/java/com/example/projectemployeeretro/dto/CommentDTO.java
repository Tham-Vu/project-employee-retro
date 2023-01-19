package com.example.projectemployeeretro.dto;

import com.example.projectemployeeretro.entity.Employee;
import com.example.projectemployeeretro.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO implements Serializable {
    private Long evaluatorId;
    private Long evaluateeId;
    private Long projectId;
    private String comment;
    private LocalDate dateCreate;
    private Float point;
}
