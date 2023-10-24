package com.kramti.controller;

import com.kramti.dto.AuthRequest;
import com.kramti.service.UserService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/api/login")
public class LoginController {
    @Inject
    UserService userService;

    @POST
    public Response login(@Valid AuthRequest authRequest){
        return this.userService.login(authRequest);
    }
}
