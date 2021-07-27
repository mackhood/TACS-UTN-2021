package com.example.TACS2021UTN.repositories.attributes;

import com.example.TACS2021UTN.models.attribute.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAttributeRepository extends JpaRepository<Attribute, Long> {
    Optional<Attribute> findById(Long id);
    Optional<Attribute> findByName(String name);
}
