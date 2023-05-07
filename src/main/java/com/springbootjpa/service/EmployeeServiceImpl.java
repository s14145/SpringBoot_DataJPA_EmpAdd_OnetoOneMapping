package com.springbootjpa.service;

import com.springbootjpa.entity.Employee;
import com.springbootjpa.exception.EmployeeNotFoundException;
import com.springbootjpa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("EmployeeService")
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee getEmployeeById(Long id){
        Optional<Employee> employeeById = employeeRepository.findById(id);
        if(!employeeById.isPresent()){
            throw new EmployeeNotFoundException(HttpStatus.NOT_FOUND, "Employee with id " + id + " not found.");
        }
        Employee employee = employeeById.get();
        return employee;
    }

    public List<Employee> getEmployees(){
        List<Employee> employees = employeeRepository.findAll();
        if(employees.isEmpty()){
            throw new EmployeeNotFoundException(HttpStatus.NOT_FOUND, "No Employees found!!!");
        }
        return employees;
    }

    public List<Employee> persistEmployee(List<Employee> employees){
        List<Employee> savedEmployees = employeeRepository.saveAll(employees);
        return savedEmployees;
    }
}
