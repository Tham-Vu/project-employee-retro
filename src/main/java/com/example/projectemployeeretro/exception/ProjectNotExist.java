package com.example.projectemployeeretro.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ProjectNotExist extends Exception{
    public ProjectNotExist(String error){
        super(error);
    }
}
