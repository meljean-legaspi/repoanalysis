package com.example.repoanalysis.controllers;

import com.example.repoanalysis.services.RepoInfoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
public class RepoControllerTests {
    private MockMvc mockMvc;

    @Autowired
    private RepoInfoService repoInfoService;

    @BeforeEach
    public void setup() {
        this.mockMvc = standaloneSetup(new RepoController(repoInfoService)).build();
    }

    @Test
    public void getContributorsGivenRepoName_ShouldReturnContributorsPage() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/repo/contributors?name=mono/mono"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("users"));
    }

    @Test
    public void getContributorsGivenWrongParam_ShouldReturnBadRequest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/repo/contributors?test=mono"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void getCommitStatsGivenRepoName_ShouldReturnCommitStatsPage() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/repo/commits/stats?name=mono/mono"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("commitstats"));
    }

    @Test
    public void getCommitUserStatsGivenWrongParam_ShouldReturnBadRequest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/repo/commits/stats?test=mono"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
