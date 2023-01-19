package com.example.projectemployeeretro.restemplate_webclient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeClientList {
    public List<EmployeeClient> result;
    public Object targetUrl;
    public boolean success;
    public Object error;
    public boolean unAuthorizedRequest;
    public boolean __abp;
}
