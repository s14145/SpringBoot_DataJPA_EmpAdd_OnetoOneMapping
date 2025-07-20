package com.springbootjpa.repository;

import com.springbootjpa.entity.Address;
import com.springbootjpa.entity.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.auditing.config.AuditingBeanDefinitionRegistrarSupport;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void deleteByIdTestSuccess() {
        Employee emp1 = new Employee(100L, "Hari", 30, new Address(100L,"Mumbai","Home"));
        Employee emp2 = new Employee(101L, "Arvind", 55, new Address(101L,"Delhi","Home"));
        Employee emp3 = new Employee(102L, "Danish", 27, new Address(102L,"Kolkata","Office"));

        testEntityManager.merge(emp1);
        testEntityManager.merge(emp2);
        testEntityManager.merge(emp3);

        employeeRepository.deleteById(emp1.getEmployee_Id());


        //boolean checkIdPresent = employeeRepository.findById(employeeId).isPresent();

       assertEquals(2, employeeRepository.findAll().size());
    }
}