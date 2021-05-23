package com.example.TACS2021UTN.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GameDTO {

    public PlayerGameDTO creatorUsername;
    public PlayerGameDTO challengedUsername;
    public String deckName;
    public Integer actualNumberCards;

}
