package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;


@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Page<Employee> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    public void createEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployeesByDepartment(String depName) {
        List<Employee> empList = employeeRepository.findByDepartmentName(depName);
        return empList.stream().collect(Collectors.toList());
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Integer empId) {
        employeeRepository.deleteById(empId);
    }

    @Override
    public Map<String, Long> getAllEmployeesByDeptCount() {
        List<Employee> empList = employeeRepository.findAll();
        Map<String, Long> dataMap = empList.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartmentName,
                        TreeMap::new,
                        Collectors.counting()));
        return dataMap;
    }

    @Override
    public void deleteEmployeeByDept(String dept) {
        employeeRepository.deleteByDepartmentName(dept);
    }


}
