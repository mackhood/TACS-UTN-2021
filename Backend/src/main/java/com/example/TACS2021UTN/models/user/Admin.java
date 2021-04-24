package com.example.TACS2021UTN.models.user;

import com.example.TACS2021UTN.models.Card;
import com.example.TACS2021UTN.models.Deck;
import com.example.TACS2021UTN.service.deck.IDeckService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.List;

@Entity
public class Admin extends User{

}
