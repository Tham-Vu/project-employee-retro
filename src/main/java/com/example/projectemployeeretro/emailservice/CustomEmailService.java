package com.example.projectemployeeretro.emailservice;

import com.example.projectemployeeretro.entity.Employee;
import com.example.projectemployeeretro.repository.EmployeeRepository;
import com.example.projectemployeeretro.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;

@Service
public class CustomEmailService {
    @Autowired
    public JavaMailSender mailSender;
    @Autowired
    private EmployeeRepository repository;
    @Async
    public void sendMail() throws MessagingException, InterruptedException {
        Thread.sleep(5000);
        List<Employee> employees = repository.findAll();
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
        Long start = System.currentTimeMillis();
        for (Employee e:employees) {
            String htmlMsg = "<h3>Im testing send a HTML email</h3>"
                    + "<img src='https://ncc.asia/assets/images/logo.png'>";
            message.setContent(htmlMsg, "text/html");

            FileSystemResource file = new FileSystemResource(new File(""));
            helper.addAttachment("Demo Mail", file);
            if (e.getEmail().isEmpty()) {
                helper.setTo("joycevu142857@gmail.com");
            }else helper.setTo(e.getEmail());
            helper.setSubject("Demo Send Email");

            mailSender.send(message);
        }
        Long end = System.currentTimeMillis();
        System.out.println(" +================+" + (end - start));
    }
}
