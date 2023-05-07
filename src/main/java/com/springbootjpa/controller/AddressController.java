package com.springbootjpa.controller;

import com.springbootjpa.entity.Address;
import com.springbootjpa.repository.AddressRepository;
import com.springbootjpa.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    public ResponseEntity<List<Address>> getAllAddress(){
       List<Address> addressList = addressService.getAddresses();
       return new ResponseEntity<>(addressList,HttpStatus.OK);
    }
}
