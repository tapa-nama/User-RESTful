package com.thoughtworks.grad.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.grad.domain.User;
import com.thoughtworks.grad.repository.UserStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class UserControllerTest {
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = standaloneSetup(controller()).build();
        UserStorage.clear();
    }

    private UserController controller() {
        return new UserController();
    }

    @Test
    void should_get_url_from_server() throws Exception {
        UserStorage.save(new User(1, "zhou"));
        UserStorage.save(new User(2, "lan"));

        mockMvc.perform(get("/api/users")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("zhou"))
                .andExpect(jsonPath("$[1].name").value("lan"));
    }


    @Test
    void should_create_new_user() throws Exception {
        User newUser = new User(3, "wuqian");
        int beforeSize = UserStorage.getUsers().size();


        mockMvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(newUser))).andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.name").value("wuqian"));

        int afterSize = UserStorage.getUsers().size();

        assertThat(afterSize).isEqualTo(beforeSize + 1);
    }

    @Test
    void should_update_user() throws Exception {
        User user = new User(1, "xin kuan");
        UserStorage.save(user);
        User newUser = new User(1, "zeng zhipeng");

        int beforeSize = UserStorage.getUsers().size();

        mockMvc.perform(put("/api/users/1").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(newUser))).andExpect(status().isAccepted())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("zeng zhipeng"));

        int afterSize = UserStorage.getUsers().size();

        assertThat(afterSize).isEqualTo(beforeSize);
    }
}
