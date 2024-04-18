package com.EatEasy.auth;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
@Data
public class LogInRequest {
    @Email(message = "Debe ser una dirección de correo electrónico válida")
    private String email;

    @NotBlank(message = "La contraseña no puede estar en blanco")
    private String password;
}
