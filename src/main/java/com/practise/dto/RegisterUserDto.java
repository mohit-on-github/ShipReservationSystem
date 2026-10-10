package com.practise.dto;

import lombok.Data;

@Data
public class RegisterUserDto {
    private String userName;
    private String userEmail;
    private String userPass;
    private int age;
    private String mobNo;
}
