package com.example.TACS2021UTN;

import com.example.TACS2021UTN.DTO.request.DeckRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DeckControllerTests {

    @Autowired
    MockMvc mockMvc;


    @Test
    void getAllDecks() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/decks")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value("5"));
    }

    @Test
    void getDeckById() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/decks/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Mazo 1"));
    }

    @Test
    void getDeckById_DeckNotFound() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/decks/{id}", 10)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteDeckById() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/decks/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

        this.mockMvc.perform(MockMvcRequestBuilders.get("/decks")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value("5"));

    }

    @Test
    void createNewDeck() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/decks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(new DeckRequestDTO("01A", Arrays.asList(1L,2L)))))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




}
