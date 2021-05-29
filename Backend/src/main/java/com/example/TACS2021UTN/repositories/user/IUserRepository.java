package com.example.TACS2021UTN.repositories.user;

import com.example.TACS2021UTN.models.user.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {
    Optional<User> findById(Long id);
    Optional<User> findByUserName(String username);
    void save(User user);
    Boolean usernameExists(String username);
    List<User> findAllMatchingUsername(String username, Integer page);
}
