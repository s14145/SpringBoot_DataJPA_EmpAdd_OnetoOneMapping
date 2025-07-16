package com.springbootjpa.service;

import com.springbootjpa.entity.Address;

import com.springbootjpa.exception.AddressNotFoundException;
import com.springbootjpa.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
   private AddressRepository addressRepository;

    public List<Address> getAddresses(){
      List<Address> addressList = addressRepository.findAll();
      if(CollectionUtils.isEmpty(addressList)){
          throw new AddressNotFoundException(HttpStatus.NOT_FOUND, "No Address found!!!");
      }
      return addressList;
    }
}
