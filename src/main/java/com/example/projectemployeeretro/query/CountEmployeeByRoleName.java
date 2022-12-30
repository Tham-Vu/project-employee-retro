package com.example.projectemployeeretro.query;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CountEmployeeByRoleName {
    private String role_name;
    private Long employee_id;

    public CountEmployeeByRoleName(String role_name, Long employee_id) {
        this.role_name = role_name;
        this.employee_id = employee_id;
    }
}
