package com.example.demo.service;

import com.example.demo.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    Page<Employee> getAllEmployees(Pageable pageable);

    void createEmployee(Employee employee);

    List<Employee> getAllEmployeesByDepartment(String depName);

    Employee updateEmployee(Employee employee);

    void deleteEmployee(Integer empId);

    Map<String, Long> getAllEmployeesByDeptCount();

    //delete by dept
    void deleteEmployeeByDept(String dept);
}
