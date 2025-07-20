package com.springbootjpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "employee_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee implements Serializable {

    private static final long serialVersionUID = -9144413108929194186L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_Id")
    private Long employee_Id;

    private String empName;

    private int empAge;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_address_id")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @JsonManagedReference
    private Address address;

    public Employee(Long employee_Id, String empName, int empAge){
        this.employee_Id = employee_Id;
        this.empName = empName;
        this.empAge = empAge;
    }
}