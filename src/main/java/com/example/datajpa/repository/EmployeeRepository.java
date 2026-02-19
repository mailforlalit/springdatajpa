package com.example.datajpa.repository;


import com.example.datajpa.entity.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    //Rule findBy + Property
    List<Employee> findByEmployeeName(String empName);
    //Rule get Employee with highest salary
    Employee findFirstByOrderBySalaryDesc();
    // JPQL
    @Query(value="Select e from Employee e where e.department= :dept")
    List<Employee> findByDepartment(@Param("dept") String dept);
    //Native Query
    @Query(value="Select * from employees where salary > :sal", nativeQuery = true)
    List<Employee> findBySalaryGreaterThan(@Param("sal") double salary);
    //Native Query
    @NativeQuery(value="Select * from employees where name = :name")
    List<Employee> findByName(@Param("name") String name);

}
