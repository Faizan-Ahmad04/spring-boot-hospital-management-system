package com.faizan.hospitalManagementSystem.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LoginRequestDto {
    private String username;
    private String password;
}
