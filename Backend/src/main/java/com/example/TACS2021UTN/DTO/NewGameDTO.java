package com.example.TACS2021UTN.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewGameDTO {
    public String creatorUsername;
    public String challengedUsername;
    public String deckName;
}
