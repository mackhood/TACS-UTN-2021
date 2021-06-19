package com.example.TACS2021UTN.repositories.user;

import com.example.TACS2021UTN.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    //void save(User user);
    //Boolean usernameExists(String username);
    @Query("FROM User u where u.username like %:username%")
    List<User> findAllMatchingUsername(String username);

}
