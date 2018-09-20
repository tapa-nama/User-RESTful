package com.thoughtworks.grad.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.grad.domain.Contact;
import com.thoughtworks.grad.domain.User;
import com.thoughtworks.grad.repository.UserStorage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    void should_get_users_from_server() throws Exception {
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

    @Test
    void should_delete_user() throws Exception {
        User user = new User(1, "xin kuan");
        UserStorage.save(user);


        mockMvc.perform(delete("/api/users/1")).andExpect(status().isNoContent());

        assertThat(UserStorage.getUsers().size()).isEqualTo(0);
        assertThat(UserStorage.getUserById(1)).isNull();
    }


    private void createBasicUserWithContacts() {
        Contact contact1 = new Contact(1, "lan yixing", "male", 18, "18200288371");
        Contact contact2 = new Contact(2, "zeng zhipeng", "male", 19, "18200288372");
        ArrayList<Contact> contacts = new ArrayList<>();
        contacts.add(contact1);
        contacts.add(contact2);
        User user5 = new User(5, "zhou tian", contacts);
        UserStorage.save(user5);
    }

    @Test
    void should_create_contact_for_user() throws Exception {
        createBasicUserWithContacts();
        Contact contact3 = new Contact(3, "xin kuan", "male", 17, "18200288373");

        mockMvc.perform(post("/api/users/5/contacts").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(contact3))).andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.name").value("zhou tian"))
                .andExpect(jsonPath("$.contacts[0].id").value(1))
                .andExpect(jsonPath("$.contacts[0].name").value("lan yixing"))
                .andExpect(jsonPath("$.contacts[2].id").value(3))
                .andExpect(jsonPath("$.contacts[2].name").value("xin kuan"))
                .andExpect(jsonPath("$.contacts[2].number").value("18200288373"));
    }


    @Test
    void should_find_contacts_of_user() throws Exception {
        createBasicUserWithContacts();
        mockMvc.perform(get("/api/users/5/contacts")).andExpect(status().isOk())
                .andExpect(jsonPath("$.contacts", hasSize(2)))
                .andExpect(jsonPath("$.contacts[0].id").value(1))
                .andExpect(jsonPath("$.contacts[0].name").value("lan yixing"))
                .andExpect(jsonPath("$.contacts[1].id").value(2))
                .andExpect(jsonPath("$.contacts[1].name").value("zeng zhipeng"))
                .andExpect(jsonPath("$.contacts[1].number").value("18200288372"));
    }

    @Test
    void should_update_a_contact_of_user() throws Exception {
        createBasicUserWithContacts();
        Contact newContact = new Contact(2, "wu qian", "female", 18, "18200288380");
        int beforeSize = UserStorage.getUserById(5).getContacts().size();
        mockMvc.perform(put("/api/users/5/contacts").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(newContact))).andExpect(status().isAccepted())
                .andExpect(jsonPath("$.contacts", hasSize(2)))
                .andExpect(jsonPath("$.contacts[1].id").value(2))
                .andExpect(jsonPath("$.contacts[1].name").value("wu qian"))
                .andExpect(jsonPath("$.contacts[1].gender").value("female"));

        int afterSize = UserStorage.getUserById(5).getContacts().size();

        assertThat(afterSize).isEqualTo(beforeSize);
    }

    @Test
    void should_delete_a_contact_of_user() throws Exception {
        createBasicUserWithContacts();
        mockMvc.perform(delete("/api/users/5/contacts/1")).andExpect(status().isNoContent());
        assertThat(UserStorage.getUserById(5).getContacts().size()).isEqualTo(1);
    }

    @AfterEach
    void tearDown() {
        UserStorage.clear();
    }
}
