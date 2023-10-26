package com.kramti.dto;

import com.kramti.config.AppConfig;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterRequest {
    @Pattern(regexp = "[a-zA-Z]{3,32}", message = AppConfig.INVALID_USERNAME_MESSAGE)
    private String username;
    @Size(min = 8, max = 128, message = AppConfig.INVALID_PASSWORDS_MESSAGE)
    private String password;
    @Size(min = 8, max = 128, message = AppConfig.INVALID_PASSWORDS_MESSAGE)
    private String confirmPassword;
}
