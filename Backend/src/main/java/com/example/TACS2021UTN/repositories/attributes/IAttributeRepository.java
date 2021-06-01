package com.example.TACS2021UTN.repositories.attributes;

import com.example.TACS2021UTN.models.attribute.Attribute;

import java.util.List;
import java.util.Optional;

public interface IAttributeRepository {
    List<Attribute> findAll();
    Optional<Attribute> findById(Long id);
    Optional<Attribute> findByName(String name);
}
