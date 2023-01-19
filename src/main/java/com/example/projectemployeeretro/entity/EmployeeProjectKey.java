package com.example.projectemployeeretro.entity;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class EmployeeProjectKey implements Serializable {
    private static final long serialVersionUID = 3566798778522213354L;
    private Long employeeId;
    private Long projectId;
}
