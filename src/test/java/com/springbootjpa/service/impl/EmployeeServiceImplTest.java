package com.springbootjpa.service.impl;

import com.springbootjpa.entity.Address;
import com.springbootjpa.entity.Employee;
import com.springbootjpa.exception.EmployeeNotFoundException;
import com.springbootjpa.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl employeeServiceImpl;

    @Mock
    private EmployeeRepository employeeRepository;

    private AutoCloseable closeable;

    private Employee emp1;

    private List<Employee> employeeList;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);

        emp1 = new Employee();
        emp1.setEmployee_Id(100L);
        emp1.setEmpName("Hari");
        emp1.setEmpAge(30);
        emp1.setAddress(new Address(100L,"Mumbai","Home"));

        employeeList = Arrays.asList(
                new Employee(100L, "Hari", 30, new Address(100L,"Mumbai","Home")),
                new Employee(101L, "Arvind", 55, new Address(101L,"Delhi","Home")),
                new Employee(102L, "Danish", 27, new Address(102L,"Kolkata","Office")),
                new Employee(103L, "Sam", 19, new Address(103L,"Kashmir","Home")));
    }

    @AfterEach
    void tearDown() throws Exception{
        closeable.close();
    }

    @Test
    void getEmployeeByIdTestSuccess() {

        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(emp1));
        Employee actualEmployee = employeeServiceImpl.getEmployeeById(anyLong());

        assertNotNull(actualEmployee);
        assertEquals(emp1, actualEmployee);

    }

    @Test
    void getEmployeeByIdTestException() {

        //when(employeeRepository.findById(anyLong())).thenThrow(EmployeeNotFoundException.class);

        assertThrows(EmployeeNotFoundException.class, () -> employeeServiceImpl.getEmployeeById(null));
        verify(employeeRepository, never()).findById(anyLong());
    }

    @Test
    void getEmployeeByIdTestExceptionMessage() {

        when(employeeRepository.findById(100L)).thenThrow(new EmployeeNotFoundException("Employee with id 100L not found."));
        EmployeeNotFoundException thrown = assertThrows(EmployeeNotFoundException.class, () -> {
            employeeServiceImpl.getEmployeeById(100L);
        });

        assertEquals("Employee with id 100L not found.", thrown.getMessage());
    }

    @Test
    void getEmployeesTestSuccess() {

        when(employeeRepository.findAll()).thenReturn(employeeList);
        List<Employee> actualEmployees = employeeServiceImpl.getEmployees();

        assertEquals(employeeList, actualEmployees);
    }

    @Test
    void getEmployeesTestException() {

        when(employeeRepository.findAll()).thenReturn(null);

        assertThrows(EmployeeNotFoundException.class, () -> employeeServiceImpl.getEmployees());
    }

    @Test
    void getEmployeesTestException1() {

        when(employeeRepository.findAll()).thenThrow(new EmployeeNotFoundException("No Employees found!!!"));
        EmployeeNotFoundException thrown = assertThrows(EmployeeNotFoundException.class, () -> employeeServiceImpl.getEmployees());

        assertEquals("No Employees found!!!", thrown.getMessage() );
    }

    @Test
    void persistEmployeeTest() {

        when(employeeRepository.saveAll(employeeList)).thenReturn(employeeList);
        List<Employee> actualEmployeeList = employeeServiceImpl.persistEmployee(employeeList);

        assertEquals(employeeList, actualEmployeeList);
    }

    @Test // Testing for void method
    void deleteEmployeeByIdTestSuccess(){

       doNothing().when(employeeRepository).deleteById(anyLong());
       employeeServiceImpl.deleteEmployeeById(anyLong());

       verify(employeeRepository, times(1)).deleteById(anyLong());
    }
}