package com.example.repoanalysis.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
public class BookmarkControllerTests {
    private MockMvc mockMvc;

    @Autowired
    private BookmarkController bookmarkController;

    @BeforeEach
    public void setup() {
        this.mockMvc = standaloneSetup(bookmarkController).build();
    }

    @Test
    public void getBookmarks_ShouldReturnOkResponse() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/bookmarks"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }
}
