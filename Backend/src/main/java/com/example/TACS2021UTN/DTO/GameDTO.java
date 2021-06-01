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
    public PlayerGameDTO creator;
    public PlayerGameDTO challenged;
    public String deckName;
    public Integer actualNumberCards;
    public String state;
    public Integer stateCode;
}
