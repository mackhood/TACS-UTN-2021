package com.example.TACS2021UTN;

import com.example.TACS2021UTN.DTO.request.DeckRequestDTO;
import com.example.TACS2021UTN.DTO.request.LoginRequestDTO;
import com.example.TACS2021UTN.DTO.request.UserRegisterRequestDTO;
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
public class UserControllerTests {

    @Autowired
    MockMvc mockMvc;

    private String token;

    @BeforeEach
    void setUp() throws Exception {
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(new LoginRequestDTO("player", "player"))))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String tokenSinBearer = JsonPath.read(result.getResponse().getContentAsString(), "$.token");
        token = "Bearer " + tokenSinBearer;
    }

    @Test
    void createNewUser() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", token)
                .content(asJsonString(new UserRegisterRequestDTO("contra123", "miUsuario@gmail.com", "usuarioNoob"))))
                .andDo(print())
                .andExpect(status().isCreated());

        this.mockMvc.perform(MockMvcRequestBuilders.get("/users/{userName}", "usuarioNoob")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", token))
                .andDo(print())
                .andExpect(jsonPath("$.email").value("miUsuario@gmail.com"))
                .andExpect(jsonPath("$.username").value("usuarioNoob"));
        ;
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
