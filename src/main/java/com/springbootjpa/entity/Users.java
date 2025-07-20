package com.springbootjpa.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Users implements Serializable {

    private static final long serialVersionUID = 119988022764389941L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_Id;

    @Column(nullable = false)
    @Size(min = 4, max = 20, message = "Username should be between 4 and 20 letters.")
    @NotBlank(message = "Username should not be null or empty.")
    private String username;

    @NotBlank(message = "Password should not be null or empty.")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_priveleges",
               joinColumns = @JoinColumn(name = "user_Id", referencedColumnName = "user_Id"),
               inverseJoinColumns = @JoinColumn(name = "privilege_Id", referencedColumnName = "privilege_Id"))
    private Set<Privilege> privileges;

    public Users(String username, String password, Set<Privilege> privileges) {
        this.username = username;
        this.password = password;
        this.privileges = privileges;
    }
}