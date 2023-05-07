package com.springbootjpa.controller;

import com.springbootjpa.entity.Employee;
import com.springbootjpa.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") final Long id){
        Employee employee = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees(){
       List<Employee> employeeList = employeeService.getEmployees();
       return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<List<Employee>> createEmployee(@RequestBody List<Employee> employees){
        List<Employee> employeeList = employeeService.persistEmployee(employees);
        return new ResponseEntity<>(employeeList, HttpStatus.CREATED);
    }
}
