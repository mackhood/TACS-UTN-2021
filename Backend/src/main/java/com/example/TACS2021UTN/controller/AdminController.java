package com.example.TACS2021UTN.controller;

import com.example.TACS2021UTN.functions.JSONWrapper;
import com.example.TACS2021UTN.models.Deck;
import com.example.TACS2021UTN.models.user.Admin;
import com.example.TACS2021UTN.exceptions.DeckNotFoundException;
import com.example.TACS2021UTN.service.admin.IAdminService;
import com.example.TACS2021UTN.service.deck.IDeckService;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @Autowired
    private IDeckService deckService;




    @GetMapping("/admins/{id}")
    public ResponseEntity<Admin> getAdmin(@PathVariable(value = "id") Long id) { //throws AdminNotFoundException {

        Admin admin1 = new Admin();
        admin1.setId(id);
        admin1.setName("admin1");

        return ResponseEntity.ok(admin1);
    }

    @GetMapping("/admins")
    public  ResponseEntity<JSONWrapper> getAdmins() {


        // TODO Meter en SERVICE
        List<Admin> admins = new ArrayList<>();

        Admin admin1 = new Admin();
        admin1.setId((long) 1);
        admin1.setName("admin1");
        admins.add(admin1);

        Admin admin2 = new Admin();
        admin1.setId((long) 2);
        admin1.setName("admin2");
        admins.add(admin2);

        return ResponseEntity.ok(new JSONWrapper<>((List<Admin>) admins));

    }

    @DeleteMapping("/admins/{id}")
    public ResponseEntity<Admin> deleteAdmin(@PathVariable(value = "id") Long adminId)
    {
        return ResponseEntity.noContent().build();
    }

    /*
        /decks - GET
        /decks/id - GET
        /decks/id - POST   - solo usuario admin
        /decks/id - PUT    - solo usuario admin
        /decks/id - DELETE - solo usuario admin
     */





}
