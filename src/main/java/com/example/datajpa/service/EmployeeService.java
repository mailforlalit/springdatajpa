package com.example.datajpa.service;

import com.example.datajpa.entity.Employee;
import com.example.datajpa.entity.EmployeeProfile;
import com.example.datajpa.projection.EmployeeDTO;
import com.example.datajpa.projection.EmployeeView;
import com.example.datajpa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // This Execute All Queries
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    //N+1
    /*public List<Employee> getAllEmployees()
    {
        return employeeRepository.findAllWithEmployee();
    }*/

    public String addEmployee(Employee employee){
        if(employee.getEmployeeProfile() != null)
            employee.getEmployeeProfile().setEmployee(employee); // O2O

        if(employee.getProjects() != null)
            employee.getProjects().forEach(p -> p.setEmployee(employee)); // O2M

        if(employee.getSkills() != null)
            employee.getSkills().forEach(s -> s.getEmployees().add(employee)); // M2M

        employeeRepository.save(employee);
        return "Employee added successfully";
    }
    //public List<EmployeeView>  getEmployeesByName(String name){
    public List<EmployeeDTO>  getEmployeesByName(String name){
        return employeeRepository.findByEmployeeName(name);
    }

    public Employee getEmployeeWithHighestSalary(){
        return employeeRepository.findFirstByOrderBySalaryDesc();
    }

    public List<Employee> findEmployeesWithSalaryGreaterThan(Double salary) {
        return employeeRepository.findBySalaryGreaterThan(salary);
    }

    public List<Employee> findEmployeesByPagination(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("salary").descending());
        return employeeRepository.findAll(pageable).getContent();
    }
}
