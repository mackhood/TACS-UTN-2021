package com.example.TACS2021UTN.models.attribute;

import com.example.TACS2021UTN.models.PersistantEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Attribute extends PersistantEntity {
    private String name;
}
