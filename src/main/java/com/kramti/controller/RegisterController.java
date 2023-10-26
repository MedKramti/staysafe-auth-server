package com.kramti.controller;

import com.kramti.dto.RegisterRequest;
import com.kramti.service.UserService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/api/register")
public class RegisterController {
    @Inject
    UserService userService;
    @POST
    public Response register(@Valid RegisterRequest registerRequest){
        return this.userService.register(registerRequest);
    }
}
