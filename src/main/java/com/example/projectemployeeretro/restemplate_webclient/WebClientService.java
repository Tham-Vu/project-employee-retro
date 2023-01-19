package com.example.projectemployeeretro.restemplate_webclient;

import com.example.projectemployeeretro.dto.EmployeeDTO;
import com.example.projectemployeeretro.entity.Employee;
import com.example.projectemployeeretro.exception.EmployeeAlreadyExist;
import com.example.projectemployeeretro.repository.EmployeeProjectRepo;
import com.example.projectemployeeretro.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WebClientService {
    @Autowired
    WebClient webClient;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper mapper;

    public List<EmployeeClient> get(){
        Mono<EmployeeClientList> objects = webClient.get().uri("")
                .retrieve()
                .bodyToMono(EmployeeClientList.class);
        EmployeeClientList employeeClientList = objects.block();
        return employeeClientList.getResult();

    }
    @Transactional
    public List<EmployeeDTO> syncData(){
        List<EmployeeClient> employeeClients = get();
        List<Employee> employees = new ArrayList<>();
        for (EmployeeClient e:employeeClients) {
            Employee employee = new Employee();
            employee.setFullName(e.getLastName()+" " + e.getFirstName());
            employee.setEmail(e.getEmail());
            employees.add(employee);
        }

        for (Employee employee:employees) {
            boolean isExists = employeeRepository.existsByEmail(employee.getEmail());
            if(isExists) try {
                throw new EmployeeAlreadyExist("Employee has already exist with email " + employee.getEmail());
            } catch (EmployeeAlreadyExist e) {
                throw new RuntimeException(e);
            }
        }
        List<Employee> employeeList = employeeRepository.saveAll(employees);
        return employeeList.stream().map(e->mapper.map(e, EmployeeDTO.class)).collect(Collectors.toList());
    }
}
