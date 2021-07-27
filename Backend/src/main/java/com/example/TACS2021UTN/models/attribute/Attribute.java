package com.example.TACS2021UTN.models.attribute;

import com.example.TACS2021UTN.models.PersistantEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Attribute extends PersistantEntity {
    @Column(name = "name")
    private String name;
}
