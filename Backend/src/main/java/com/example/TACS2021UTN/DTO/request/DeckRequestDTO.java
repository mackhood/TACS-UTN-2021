package com.example.TACS2021UTN.DTO.request;

import com.example.TACS2021UTN.DTO.CardDTO;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class DeckRequestDTO {

    @NotNull(message = "El nombre no puede estar vacio")
    private String name;
    @NotNull(message = "El mazo no puede estar sin cartas")
    private List<Long> cardListId;

}
