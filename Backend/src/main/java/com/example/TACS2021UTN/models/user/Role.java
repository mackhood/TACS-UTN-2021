package com.example.TACS2021UTN.models.user;

import com.example.TACS2021UTN.models.PersistantEntity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role extends PersistantEntity {

    private String name;
}
