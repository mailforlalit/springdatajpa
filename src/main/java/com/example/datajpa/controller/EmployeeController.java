package com.example.datajpa.controller;

import com.example.datajpa.entity.Employee;
import com.example.datajpa.repository.EmployeeRepository;
import com.example.datajpa.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

   @GetMapping("/employees")
   public List<Employee> getAllEmployees(){
       return employeeService.getAllEmployees();
   }

   @PostMapping("/employees")
    public String addEmployee(@RequestBody Employee employee){
       return employeeService.addEmployee(employee);
   }

}
