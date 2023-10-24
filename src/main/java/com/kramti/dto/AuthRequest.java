package com.kramti.dto;

import com.kramti.config.AppConfig;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthRequest {
    @Pattern(regexp = "[a-zA-Z]{3,32}", message = AppConfig.INVALID_LOGIN)
    @NotNull(message = AppConfig.INVALID_LOGIN)
    private String username;
    @Size(max = 128, message = AppConfig.INVALID_LOGIN)
    @NotNull(message = AppConfig.INVALID_LOGIN)
    private String password;
}
