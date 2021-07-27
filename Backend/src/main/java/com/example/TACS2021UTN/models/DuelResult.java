package com.example.TACS2021UTN.models;

import com.example.TACS2021UTN.models.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DuelResult extends PersistantEntity{
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User winner;
    @Enumerated(EnumType.STRING)
    @Column(name = "result")
    private EResult result;
}
