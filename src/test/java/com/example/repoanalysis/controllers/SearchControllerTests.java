package com.example.repoanalysis.controllers;

import com.example.repoanalysis.services.SearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
public class SearchControllerTests {
    private MockMvc mockMvc;

    @Autowired
    private SearchService searchService;

    @BeforeEach
    public void setup() {
        this.mockMvc = standaloneSetup(new SearchController(searchService)).build();
    }

    @Test
    public void givenSearchQuery_ShouldReturnResponse() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/search?query=mono"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("results"));
    }

    @Test
    public void givenWrongParam_ShouldReturnBadRequest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/search?test=mono"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
