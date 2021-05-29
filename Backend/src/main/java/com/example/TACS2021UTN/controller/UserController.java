package com.example.TACS2021UTN.controller;

import com.example.TACS2021UTN.DTO.RoleDTO;
import com.example.TACS2021UTN.DTO.UserDTO;
import com.example.TACS2021UTN.DTO.request.UserRegisterRequestDTO;
import com.example.TACS2021UTN.models.user.User;
import com.example.TACS2021UTN.service.user.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins ="*",maxAge = 3600)
@RestController
public class UserController {

    private final IUserService service;
    public UserController(IUserService service){
        this.service = service;
    }

    @GetMapping("/users/{userName}")
    public ResponseEntity<UserDTO> getUserByID(@PathVariable String userName)
    {
        return ResponseEntity.ok(service.findByUserName(userName));
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody UserRegisterRequestDTO user){
        try{
            service.save(user);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
