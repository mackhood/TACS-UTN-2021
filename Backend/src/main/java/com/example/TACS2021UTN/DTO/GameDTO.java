package com.example.TACS2021UTN.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameDTO {

    public Long id;
    public PlayerGameDTO creatorUsername;
    public PlayerGameDTO challengedUsername;
    public String deckName;
    public Integer actualNumberCards;

}
