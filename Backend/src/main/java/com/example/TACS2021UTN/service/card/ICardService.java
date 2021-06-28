package com.example.TACS2021UTN.service.card;

import com.example.TACS2021UTN.DTO.CardApiDTO;
import com.example.TACS2021UTN.DTO.CardDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICardService {
    List<CardDTO> getAllCards(Pageable paging);
    CardDTO findById(long id);
}
