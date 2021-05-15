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

@RestController
public class UserController {

    private final IUserService service;
    private final ModelMapper modelMapper;
    public UserController(IUserService service, ModelMapper modelMapper) {

        this.service = service;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/users/{userName}")
    public ResponseEntity<UserDTO> getUserByID(@PathVariable String userName)
    {
        return ResponseEntity.ok(this.userToDTO(service.findByUserName(userName)));
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

    private UserDTO userToDTO(User user){

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        List<RoleDTO> roleDTOList = new ArrayList<>();
        user.getRoles().forEach(role -> {
            RoleDTO roleDTO1 = new RoleDTO();
            roleDTO1.setName(role.getName());
            roleDTOList.add(roleDTO1);
        });
        userDTO.setRoles(roleDTOList);
        return userDTO;
    }
}
