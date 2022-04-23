package com.beyzanur.accountservice.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class RegisterRequest {

    private String username;
    private String email;
    private String password;
}
