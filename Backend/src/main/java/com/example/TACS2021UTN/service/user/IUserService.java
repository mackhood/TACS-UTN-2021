package com.example.TACS2021UTN.service.user;

import com.example.TACS2021UTN.DTO.TokenDTO;
import com.example.TACS2021UTN.DTO.request.LoginRequestDTO;
import com.example.TACS2021UTN.models.user.User;

public interface IUserService {
    TokenDTO authenticate(LoginRequestDTO loginRequestDTO);
    User findByUserName(String username);
}
