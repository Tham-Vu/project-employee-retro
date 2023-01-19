package com.example.projectemployeeretro.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class EmployeeNotExistInProject extends Exception{
    public EmployeeNotExistInProject(String error){
        super(error);
    }
}
