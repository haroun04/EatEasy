package com.EatEasy.auth;

import lombok.Data;

@Data
public class SignUpRequest {
    private String name;
    private String password;
}
