package com.practise.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String userEmail;
    private String userPass;
}
