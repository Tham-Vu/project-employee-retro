package com.example.projectemployeeretro.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class EmployeeNotExist extends Exception{
    public EmployeeNotExist(String message){
        super(message);
    }
}
