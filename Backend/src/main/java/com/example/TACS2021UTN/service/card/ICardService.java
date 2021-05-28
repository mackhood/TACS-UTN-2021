package com.example.TACS2021UTN.service.card;

import com.example.TACS2021UTN.DTO.CardApiDTO;
import com.example.TACS2021UTN.DTO.CardDTO;

import java.util.List;

public interface ICardService {
    List<CardDTO> getAllCards();
    CardDTO findById(long id);
}
