package com.springbootjpa.repository;

import com.springbootjpa.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("EmployeeRepository")
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    @Modifying
    @Transactional
    void deleteById(Long id);
}