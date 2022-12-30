package com.example.projectemployeeretro.emailservice;

import com.example.projectemployeeretro.entity.Employee;
import com.example.projectemployeeretro.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.mail.MessagingException;
import java.util.List;

@RestController
public class RestAPI {
    @Autowired
    public CustomEmailService emailService;
    @RequestMapping("/sendmail")
    public String sendEmail() throws MessagingException {
        emailService.sendMail();
        return "Email Sent Successfully!";
    }
    @Autowired
    private EmployeeRepository repository;
    @RequestMapping(value = "/send")
    public String send(Model model){
        List<Employee> list = repository.findAll();
        model.addAttribute("employee", list);
        return "index";
    }


}
