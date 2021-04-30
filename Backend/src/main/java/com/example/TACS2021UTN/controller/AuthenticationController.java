package com.example.TACS2021UTN.controller;

import com.example.TACS2021UTN.models.user.Admin;
import com.example.TACS2021UTN.models.user.User;
import com.example.TACS2021UTN.utils.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam("user") String username, @RequestParam("password") String password)
    {
        //TODO authenticate user, check password and username, then generate token (in service)
        String token = jwtTokenProvider.doGenerateToken(username);
        User user = new Admin();
        user.setName("PEPE EL ADMIN");
        user.setAccessToken(token);
        return ResponseEntity.ok(user);
    }
}
