package com.example.TACS2021UTN.service.user;

import com.example.TACS2021UTN.DTO.TokenDTO;
import com.example.TACS2021UTN.DTO.UserDTO;
import com.example.TACS2021UTN.DTO.request.DeckRequestDTO;
import com.example.TACS2021UTN.DTO.request.LoginRequestDTO;
import com.example.TACS2021UTN.DTO.request.UserRegisterRequestDTO;
import com.example.TACS2021UTN.exceptions.CardNotFoundException;
import com.example.TACS2021UTN.exceptions.UserAlreadyExistsException;
import com.example.TACS2021UTN.models.user.User;

public interface IUserService {
    TokenDTO authenticate(LoginRequestDTO loginRequestDTO);
    User findByUserName(String username);
    void save(UserRegisterRequestDTO user) throws UserAlreadyExistsException;

}
