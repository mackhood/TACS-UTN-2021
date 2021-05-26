package com.example.TACS2021UTN;

import com.example.TACS2021UTN.DTO.request.DeckRequestDTO;
import com.example.TACS2021UTN.DTO.request.LoginRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
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

    private String token;

    @BeforeEach
    void setUp() throws Exception {
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(new LoginRequestDTO("admin", "admin"))))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String tokenSinBearer = JsonPath.read(result.getResponse().getContentAsString(), "$.token");
        token = "Bearer " + tokenSinBearer;
    }


    @Test
    void getAllDecks() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/decks")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.size()").value("5"));
    }

    @Test
    void getDeckById() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/decks/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Mazo 1"));
    }

    @Test
    void getDeckById_DeckNotFound() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/decks/{id}", 10)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", token))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteDeckById() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/decks/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", token))
                .andDo(print())
                .andExpect(status().isNoContent());

        this.mockMvc.perform(MockMvcRequestBuilders.get("/decks")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.size()").value("5"));

    }

    @Test
    void createNewDeck() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/decks")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", token)
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
