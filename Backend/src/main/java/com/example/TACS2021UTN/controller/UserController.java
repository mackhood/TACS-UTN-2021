package com.example.TACS2021UTN.controller;

import com.example.TACS2021UTN.DTO.RoleDTO;
import com.example.TACS2021UTN.DTO.UserDTO;
import com.example.TACS2021UTN.DTO.UserSearchDTO;
import com.example.TACS2021UTN.DTO.request.UserRegisterRequestDTO;
import com.example.TACS2021UTN.functions.JSONWrapper;
import com.example.TACS2021UTN.models.user.User;
import com.example.TACS2021UTN.service.user.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins ="*",maxAge = 3600)
@RestController
public class UserController extends BaseController{

    private final IUserService service;
    public UserController(IUserService service){
        this.service = service;
    }

    @GetMapping("/users/{userName}")
    public ResponseEntity<UserDTO> getUserByID(@PathVariable String userName)
    {
        return ResponseEntity.ok(service.findByUserName(userName));
    }

    @GetMapping("/users")
    public ResponseEntity<JSONWrapper<UserSearchDTO>>getUsers(
            @RequestParam(name = "username",required = false, defaultValue = "") String username,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "4") Integer size

    )
    {
        Pageable paging = PageRequest.of(page, getPageSize(size));

        return ResponseEntity.ok(new JSONWrapper<>(service.findAllMatchingUsername(username, paging)));
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
