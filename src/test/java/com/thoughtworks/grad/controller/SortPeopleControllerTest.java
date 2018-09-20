package com.thoughtworks.grad.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class SortPeopleControllerTest {
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = standaloneSetup(controller()).build();
    }

    private SortPeopleController controller() {
        return new SortPeopleController();
    }

    @Test
    void should_return_sorted_people() throws Exception {
        mockMvc.perform(get("/api/people").param("names", "zhang san,li si")
                .param("ages", "18,9")).andExpect(status().isOk())
                .andExpect(jsonPath("$.['zhang san'].name").value("zhang san"))
                .andExpect(jsonPath("$.['zhang san'].age").value(18));
    }

    @Test
    void should_return_sorted_people_again() throws Exception {
        List<String> names = Arrays.asList("zhang san","li si");
        List<String> ages = Arrays.asList("18", "9");
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap();
        multiValueMap.put("names",names);
        multiValueMap.put("ages", ages);


        mockMvc.perform(get("/api/people").params(multiValueMap)).andExpect(status().isOk());
    }
}
