package com.kramti.service;

import com.kramti.config.AppConfig;
import com.kramti.dto.AuthRequest;
import com.kramti.dto.AuthResponse;
import com.kramti.dto.ErrorDto;
import com.kramti.dto.RegisterRequest;
import com.kramti.entity.User;
import com.kramti.repository.UserRepository;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import io.quarkus.elytron.security.common.BcryptUtil;


@ApplicationScoped
public class UserService {
    @Inject
    UserRepository userRepository;
    @Inject
    JwtService jwtService;
    @Inject
    SecurityIdentity securityIdentity;

    public Response login(AuthRequest authRequest){
        User user = this.userRepository.findByUsername(authRequest.getUsername());
        if (user == null || !this.matchPasswords(user, authRequest.getPassword())){
            // invalid login
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity(new ErrorDto(AppConfig.INVALID_LOGIN_MESSAGE))
                    .build();
        }else{
            // valid login
            return Response
                    .status(Response.Status.OK)
                    .entity(new AuthResponse(jwtService.generateToken(user)))
                    .build();
        }
    }

    private boolean matchPasswords(User user, String textPlainPassword){
        return BcryptUtil.matches(textPlainPassword, user.getPassword());
    }
    @Transactional
    public Response register(RegisterRequest registerRequest) {
        if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())){
            // password do not much
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorDto(AppConfig.UNMATCHED_PASSWORDS_MESSAGE))
                    .build();
        }

        if(this.userRepository.findByUsername(registerRequest.getUsername())!=null){
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorDto("Username exists"))
                    .build();
        }

        String hashedPassword = BcryptUtil.bcryptHash(registerRequest.getPassword());
        User user = User
                .builder()
                .username(registerRequest.getUsername())
                .password(hashedPassword)
                .role("user")
                .build();
        this.userRepository.persist(user);
        return Response.noContent().build();
    }

    public boolean isAdmin(){
        if (securityIdentity.getRoles().contains("admin")){
            return true;
        }
        return false;
    }

}
