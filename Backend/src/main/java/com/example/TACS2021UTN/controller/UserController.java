package com.example.TACS2021UTN.controller;

import com.example.TACS2021UTN.DTO.CardDTO;
import com.example.TACS2021UTN.DTO.DeckDTO;
import com.example.TACS2021UTN.DTO.RoleDTO;
import com.example.TACS2021UTN.DTO.UserDTO;
import com.example.TACS2021UTN.DTO.request.DeckRequestDTO;
import com.example.TACS2021UTN.exceptions.CardNotFoundException;
import com.example.TACS2021UTN.exceptions.DeckNotFoundException;
import com.example.TACS2021UTN.functions.JSONWrapper;
import com.example.TACS2021UTN.models.Deck;
import com.example.TACS2021UTN.models.user.Role;
import com.example.TACS2021UTN.models.user.User;
import com.example.TACS2021UTN.service.deck.IDeckService;
import com.example.TACS2021UTN.service.user.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

public class UserController {

    private final IUserService service;

    public UserController(IUserService service){

        this.service = service;
    }

    //TODO validate if it is necesary
   /* @GetMapping("/users")
    public ResponseEntity<JSONWrapper> getAllUsers()

    {

      //  return ResponseEntity.ok(new JSONWrapper<>((List<UserDTO>) service.getAllUSers()));
    } //OK*/

    @GetMapping("/users/{name}")
    public ResponseEntity<UserDTO> getUserByID(@PathVariable String userName)
    {

        return ResponseEntity.ok(userToDTO(service.findByUserName(userName)));
    } //OK

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createUser(@Valid @RequestBody UserDTO user) throws CardNotFoundException {

        try{
            service.save(dtoToUser(user));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }




    private UserDTO userToDTO(User user){

        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getUsername());
        userDTO.setId(user.getId().toString());
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

    private User dtoToUser(UserDTO user) {

        User example = new User();
        example.setUsername(user.getName());
        example.setEmail(user.getEmail());
        example.setPassword(user.getPassword());
        List<Role> roleList = new ArrayList<>();
        user.getRoles().forEach(roleDTO -> {
            Role role = new Role();
            role.setName(roleDTO.getName());
            roleList.add(role);
        });
        example.setRoles(roleList);
        return example;
    }

}
