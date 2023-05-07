package com.springbootjpa.repository;

import com.springbootjpa.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("EmployeeRepository")
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
