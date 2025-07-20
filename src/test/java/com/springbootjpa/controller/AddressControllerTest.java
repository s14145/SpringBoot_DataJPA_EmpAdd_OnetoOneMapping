package com.springbootjpa.controller;

import com.springbootjpa.entity.Address;
import com.springbootjpa.entity.Employee;
import com.springbootjpa.service.AddressService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.RequestMatcher;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;


@WebMvcTest(AddressController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class AddressControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AddressService addressService;

    @Test
    void getAllAddressTestSuccess() throws Exception {
        List<Address> addressList = Stream.of(
                new Address(100L,"Mumbai","Home",
                        new Employee(200L,"Binod",25))
                , new Address(101L,"Delhi","Office",
                        new Employee(201L,"Suresh",34))
                ,new Address(102L,"Kanput","Home",
                        new Employee(202L,"Ram",40))
        ).collect(Collectors.toList());

        Mockito.when(addressService.getAddresses()).thenReturn(addressList);

        mvc.perform(MockMvcRequestBuilders.get(("/project/api/v1/addresses")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)));
    }
}