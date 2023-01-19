package com.example.demo.controller;


import com.example.demo.entity.Employee;
import com.example.demo.model.EmployeeDto;
import com.example.demo.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/rightpoint/controller", headers = "Accept=application/json")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ModelMapper modelMapper;


    @PostMapping(path = "/createEmployee")
    @ResponseStatus( HttpStatus.CREATED )
    public void createEmployee(@RequestBody Employee employee){
        employeeService.createEmployee(employee);
    }

    @PostMapping(path = "/updateEmployee")
    @ResponseStatus( HttpStatus.OK )
    public EmployeeDto updateEmployee(@RequestBody Employee employee){
        return modelMapper.map(employeeService.updateEmployee(employee), EmployeeDto.class);
    }

    @GetMapping(path = "/getAllEmployees")
    public List<EmployeeDto> getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size){
        List<EmployeeDto> employeeList = null;
        try{
            Pageable paging = PageRequest.of(page, size);
            Page<Employee> empList = employeeService.getAllEmployees(paging);
            employeeList = empList.getContent()
                    .stream()
                    .map(employee -> modelMapper.map(employee, EmployeeDto.class))
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            System.out.println("Records not found!!!");
        }
        return employeeList;
    }

    @GetMapping(path = "/getAllEmployeeByDepartment/{dept}")
    public List<EmployeeDto> getAllEmployeeByDepartment(@PathVariable("dept") String dept){
        return employeeService.getAllEmployeesByDepartment(dept)
                .stream().map(employee -> modelMapper.map(employee, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    @DeleteMapping(path= "/deleteEmployee/{empId}")
    public void deleteEmployee(@PathVariable("empId") Integer empId){
        employeeService.deleteEmployee(empId);
    }

    @GetMapping(path = "/getAllEmployeesByDeptCount")
    public Map<String, Long> getAllEmployeesByDeptCount(){
        Map<String, Long> empMap = employeeService.getAllEmployeesByDeptCount();
        return empMap;
    }

    @DeleteMapping(path= "/deleteEmployeeByDepartment/{dept}")
    public void deleteEmployeeByDepartment(@PathVariable("dept") String dept){
        employeeService.deleteEmployeeByDept(dept);
    }
}
