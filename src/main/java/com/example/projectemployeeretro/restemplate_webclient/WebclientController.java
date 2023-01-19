package com.example.projectemployeeretro.restemplate_webclient;

import com.example.projectemployeeretro.dto.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/webclient/client-ncc1")
public class WebclientController {
    @Autowired
    private WebClientService service;

    @GetMapping
    public List<EmployeeClient> getEmployeesLocal(){
        return service.get();
    }
    @GetMapping("/sync")
    public List<EmployeeDTO> getSyncData(){
        return service.syncData();
    }
}
