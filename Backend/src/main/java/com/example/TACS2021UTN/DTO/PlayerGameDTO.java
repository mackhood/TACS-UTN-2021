package com.example.TACS2021UTN.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerGameDTO {
    public String username;
    public Integer mainCards;
    public Integer gainedCards;
    public String isMyTurn;
}
