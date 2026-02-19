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

   @GetMapping("/employees/name/{name}")
    public List<Employee> getEmployeeByName(@PathVariable String name){
       return employeeService.getEmployeesByName(name);
    }

    @GetMapping("/employees/salary")
    public Employee getTopEmployee(){
        return employeeService.getEmployeeWithHighestSalary();
    }

    @GetMapping("/employees/salary/{salary}")
    public List<Employee> getEmployeesWithSalaryGreaterThan(@PathVariable Double salary) {
        return employeeService.findEmployeesWithSalaryGreaterThan(salary);
    }

    @GetMapping("/employees/{pageNo}/{pageSize}")
    public List<Employee> getEmployeesByPagination(@PathVariable int pageNo, @PathVariable int pageSize) {
        return employeeService.findEmployeesByPagination(pageNo-1, pageSize);
    }

}
