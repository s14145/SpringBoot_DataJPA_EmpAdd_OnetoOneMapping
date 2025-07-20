package com.springbootjpa.service.impl;

import com.springbootjpa.entity.Address;
import com.springbootjpa.entity.Employee;
import com.springbootjpa.exception.AddressNotFoundException;
import com.springbootjpa.repository.AddressRepository;
import com.springbootjpa.service.impl.AddressServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddressServiceImplTest {

    @InjectMocks
    private AddressServiceImpl addressServiceImpl;

    @Mock
    private AddressRepository addressRepository;

    private AutoCloseable closeable;

    private List<Address> addressList;

    @BeforeEach
    public void setUp(){
        closeable = MockitoAnnotations.openMocks(this);

        addressList = Stream.of(
                new Address(100L,"Mumbai","Home",
                        new Employee(200L,"Binod",25))
                , new Address(101L,"Delhi","Office",
                        new Employee(201L,"Suresh",34))
                ,new Address(102L,"Kanput","Home",
                        new Employee(202L,"Ram",40))
        ).collect(Collectors.toList());
    }

    @AfterEach
    void tearDown() throws Exception{
        closeable.close();
    }


    @Test
    void getAddressesTestSuccess() {

        when(addressRepository.findAll()).thenReturn(addressList);
        List<Address> actualAddresses = addressServiceImpl.getAddresses();

        assertEquals(addressList, actualAddresses);
    }

    @Test
    void getAddressesTestCustomException(){

        //when(addressRepository.findAll()).thenReturn(null);
        when(addressRepository.findAll()).thenThrow(new AddressNotFoundException("No Address found!!!"));
        assertThrows(AddressNotFoundException.class, () -> addressServiceImpl.getAddresses());
    }

    @Test
    void getAddressesTestCustomException1(){

        when(addressRepository.findAll()).thenThrow(new AddressNotFoundException("No Address found!!!"));

        AddressNotFoundException thrown = assertThrows(AddressNotFoundException.class, () -> addressServiceImpl.getAddresses());

        assertEquals("No Address found!!!", thrown.getMessage());
    }

    @Test
    void getAddressesTestException(){

        //RuntimeException runtimeException = mock(RuntimeException.class);
        when(addressRepository.findAll()).thenThrow(new RuntimeException());
        RuntimeException runtimeException = (RuntimeException) addressServiceImpl.getAddresses();

        assertThrows(RuntimeException.class, (Executable) runtimeException);
    }
}