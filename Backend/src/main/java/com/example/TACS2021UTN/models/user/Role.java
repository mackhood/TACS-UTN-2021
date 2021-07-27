package com.example.TACS2021UTN.models.user;

import com.example.TACS2021UTN.models.PersistantEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role extends PersistantEntity {
    @Column(name = "name")
    private String name;
}
