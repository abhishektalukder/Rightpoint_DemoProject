package com.example.demo.repository;

import com.example.demo.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends MongoRepository<Employee, Integer> {

    @Override
    Page<Employee> findAll(Pageable pageable);

    @Override
    Optional<Employee> findById(Integer id);

    List<Employee> findAll();

    @Query("{departmentName: ?0}")
    List<Employee> findByDepartmentName(String departmentName);

    @DeleteQuery
    void deleteByDepartmentName(String dept);

}
