package com.springbootjpa.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Privilege")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Privilege implements Serializable {

    private static final long serialVersionUID = 1637499671371452995L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long privilege_Id;

    private String name;

    public Privilege(String name) {
        this.name = name;
    }
}