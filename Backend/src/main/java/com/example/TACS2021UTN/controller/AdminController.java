package com.example.TACS2021UTN.controller;

import com.example.TACS2021UTN.entities.Deck;
import com.example.TACS2021UTN.entities.user.Admin;
import com.example.TACS2021UTN.exceptions.AdminNotFoundException;
import com.example.TACS2021UTN.exceptions.DeckNotFoundException;
import com.example.TACS2021UTN.service.admin.IAdminService;
import com.example.TACS2021UTN.service.deck.IDeckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @Autowired
    private IDeckService deckService;

    @GetMapping("/admins/{name}")
    public Admin getAdminByName(@PathVariable(value = "name") String name) throws AdminNotFoundException {

        return this.adminService.getAdminByName(name);
    }


    @GetMapping("/admins/{id}")
    public Admin getAdminByName(@PathVariable(value = "id") Long id) throws AdminNotFoundException {

        return this.adminService.getAdminById(id);
    }


    @PostMapping("/admins")
    public Admin createAdmin(@Valid @RequestBody Admin admin) {
        return adminService.createAdmin(admin);
    }

    @DeleteMapping("/admins/{id}")
    public ResponseEntity<Admin> deleteAdmin(@PathVariable(value = "id") Long adminId) throws AdminNotFoundException {
        Admin admin = adminService.getAdminById(adminId);

        adminService.delete(admin);

        return ResponseEntity.ok().build();
    }

    /*
        /decks - GET
        /decks/id - GET
        /decks/id - POST   - solo usuario admin
        /decks/id - PUT    - solo usuario admin
        /decks/id - DELETE - solo usuario admin
     */

    @PostMapping("/admins/createDeck")
    public void createDeck(@Valid @RequestBody Deck deck) {
        deckService.createDeck(deck);
    }


    @PostMapping("/admins/deleteDeck/{id}")
    public void deleteDeck(@Valid @RequestBody Deck deck) {
        deckService.deleteDeck(deck);
    }


    @PutMapping("/admins/updateDeck/{id}")
    public Deck updateDeck(@PathVariable(value = "id") Long deckId,
                           @Valid @RequestBody Deck deckDetails) throws DeckNotFoundException{
        Deck updatedDeck = deckService.updateDeck(deckId,deckDetails);

        return updatedDeck;
    }





}
