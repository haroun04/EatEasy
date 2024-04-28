package com.EatEasy.auth;

import lombok.Data;

@Data
public class LogInRequest {
    private String name;
    private String password;
}
