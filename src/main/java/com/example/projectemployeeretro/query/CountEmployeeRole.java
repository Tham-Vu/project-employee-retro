package com.example.projectemployeeretro.query;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CountEmployeeRole {
    private Long role_id;
    private Long total;

    public CountEmployeeRole(Long role_id, Long total) {
        this.role_id = role_id;
        this.total = total;
    }
}
