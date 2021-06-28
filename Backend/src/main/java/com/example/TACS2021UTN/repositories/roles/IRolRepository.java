package com.example.TACS2021UTN.repositories.roles;

import com.example.TACS2021UTN.models.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRolRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
