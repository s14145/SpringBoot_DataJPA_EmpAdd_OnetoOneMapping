package com.springbootjpa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootjpa.entity.Address;
import com.springbootjpa.entity.Employee;
import com.springbootjpa.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


@WebMvcTest(EmployeeController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    private Employee emp1;

    private List<Employee> employeeList;

    private static final String BASE_URL = "/project/api/v1/employees";

    @BeforeEach
    void setUp() {
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

    @Test
    void getEmployeeByIdTest_Success() throws Exception {
        // TODO Check content of String in response
        //String employeeJSON = "{\"employee_Id": \100L}"

        when(employeeService.getEmployeeById(anyLong())).thenReturn(emp1);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/100L")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(emp1)));
        // TODO Check content of String in response
    }

    @Test
    void getEmployeesTest_Success() throws Exception {

        when(employeeService.getEmployees()).thenReturn(employeeList);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(employeeList)));

    }

    @Test
    void createEmployeeTest_Success() throws Exception {

        when(employeeService.persistEmployee(Collections.singletonList(ArgumentMatchers.any(Employee.class)))).thenReturn(employeeList);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employeeList))
        );

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(4)));
    }

    @Test
    void deleteEmployeeByIdTest_Success() throws Exception {

        doNothing().when(employeeService).deleteEmployeeById(anyLong());

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.delete(BASE_URL + "/100L")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isNoContent())
                .andExpect(MockMvcResultMatchers.content().string("Deleted employee with id 100L"));
    }
}