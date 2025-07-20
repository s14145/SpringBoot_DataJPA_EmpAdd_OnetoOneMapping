package com.springbootjpa.service.impl;

import com.springbootjpa.entity.Address;

import com.springbootjpa.exception.AddressNotFoundException;
import com.springbootjpa.repository.AddressRepository;
import com.springbootjpa.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> getAddresses() throws AddressNotFoundException{

        List<Address> addressList = null;

        try {
            addressList = addressRepository.findAll();
            if (CollectionUtils.isEmpty(addressList)) {
                throw new AddressNotFoundException(HttpStatus.NOT_FOUND, "No Address found!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return addressList;
    }
}