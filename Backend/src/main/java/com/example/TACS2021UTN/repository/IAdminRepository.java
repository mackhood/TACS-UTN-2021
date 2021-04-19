package com.example.TACS2021UTN.repository;

import com.example.TACS2021UTN.entities.user.Admin;
import com.example.TACS2021UTN.entities.user.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface IAdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByName(String name);
}
