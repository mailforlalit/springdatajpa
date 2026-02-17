package com.example.datajpa.service;

import com.example.datajpa.entity.Employee;
import com.example.datajpa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public String addEmployee(Employee employee){
        employeeRepository.save(employee);
        return "Employee added successfully";
    }
}
