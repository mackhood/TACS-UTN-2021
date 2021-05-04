package com.example.TACS2021UTN.controller;

import com.example.TACS2021UTN.DTO.TokenDTO;
import com.example.TACS2021UTN.DTO.request.LoginRequestDTO;
import com.example.TACS2021UTN.service.user.IUserService;
import com.example.TACS2021UTN.utils.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins ="*",maxAge = 3600)
@RestController
public class AuthenticationController {

    private JwtTokenProvider jwtTokenProvider;
    private IUserService userService;
    private AuthenticationManager authenticationManager;

    public AuthenticationController(IUserService userService, JwtTokenProvider jwtTokenProvider,
                                    AuthenticationManager authenticationManager)
    {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }




    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest)
    {
        this.authenticate(loginRequest);
        //TODO authenticate user, check password and username, then generate token (in service)
        TokenDTO tokenResponse = userService.authenticate(loginRequest);
        return ResponseEntity.ok(tokenResponse);
    }

    private void authenticate(LoginRequestDTO loginRequest){
        String password = loginRequest.getPassword();

        Authentication auth =  authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        password
                )
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
