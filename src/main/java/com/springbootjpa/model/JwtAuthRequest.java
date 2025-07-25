package com.springbootjpa.model;


import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class JwtAuthRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}