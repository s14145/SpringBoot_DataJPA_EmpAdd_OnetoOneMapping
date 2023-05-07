package com.springbootjpa.service;

import com.springbootjpa.entity.Employee;

import java.util.List;

public interface EmployeeService {

    Employee getEmployeeById(Long id);

    List<Employee> getEmployees();

    List<Employee> persistEmployee(List<Employee> employees);

}
