package com.example.TACS2021UTN.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GameDTO {

    public long creatorId;
    public long challengedId;
    public long deckId;
}
