package com.kramti.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthRequest {
    private String username;
    private String password;
}
