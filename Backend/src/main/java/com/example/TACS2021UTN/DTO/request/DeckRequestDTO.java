package com.example.TACS2021UTN.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
public class DeckRequestDTO {

    @NotNull(message = "El nombre no puede estar vacio")
    private String name;
    @NotNull(message = "El mazo no puede estar sin cartas")
    private List<Long> cardListId;

}
