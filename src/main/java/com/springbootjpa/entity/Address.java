package com.springbootjpa.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "address")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Address implements Serializable {

    private static final long serialVersionUID = -382931871163162349L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_Id")
    private Long address_Id;

    private String city;

    private String addressType;

    @OneToOne(mappedBy = "address")
    @JsonBackReference
    private Employee employee;

    public Address(Long address_Id, String city, String addressType){
        this.address_Id = address_Id;
        this.city = city;
        this.addressType = addressType;
    }
}