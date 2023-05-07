package com.springbootjpa.service;

import com.springbootjpa.entity.Address;

import com.springbootjpa.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
   private AddressRepository addressRepository;

    public List<Address> getAddresses(){
      List<Address> addressList = addressRepository.findAll();
      return addressList;
    }
}
