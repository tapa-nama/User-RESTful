package com.thoughtworks.grad.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.grad.domain.User;
import com.thoughtworks.grad.repository.UserStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class UserControllerTest {
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = standaloneSetup(controller()).build();
    }

    private UserController controller() {
        return new UserController();
    }

    @Test
    void should_get_url_from_server() throws Exception {
        mockMvc.perform(get("/api/users")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name").value("zhou"))
                .andExpect(jsonPath("$[1].name").value("lan"));
    }

    @Test
    void should_create_new_user() throws Exception {

    }
}
