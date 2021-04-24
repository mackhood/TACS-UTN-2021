package com.example.TACS2021UTN.repositories;

import com.example.TACS2021UTN.models.user.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface IAdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByName(String name);
}
