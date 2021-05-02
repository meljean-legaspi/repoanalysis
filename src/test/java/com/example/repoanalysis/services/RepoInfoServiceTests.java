package com.example.repoanalysis.services;

import com.example.repoanalysis.entities.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class RepoInfoServiceTests {
    @Autowired
    private RepoInfoService repoInfoService;

    @Test
    public void testIfPerPageLimitIsGivenForContributorsSearch_ContributorsEqualLimit(){
        RepoInfoRequest request = new RepoInfoRequest();
        request.setRepoFullName("mono/mono");
        request.setResultsPerPage(5);

        List<Contributor> contributors = repoInfoService.getContributors(request);
        assertEquals(5, contributors.size());
    }

    @Test
    public void testGetCommitterStats_ShouldReturnNumberOfCommitsPerUser(){
        RepoInfoRequest request = new RepoInfoRequest();
        request.setRepoFullName("mono/mono");

        Map<String, Long> userStats = repoInfoService.getCommitsPerUser(request);
        assertNotEquals(0, userStats.size());
        assertNotEquals(0,userStats.get("lambdageek").intValue());
    }

    @Test
    public void testGetCommitsTimeline_ShouldReturnNumberOfCommitsByDate(){
        RepoInfoRequest request = new RepoInfoRequest();
        request.setRepoFullName("mono/mono");

        Map<String, Long> commitsTimeline = repoInfoService.getCommitsTimeline(request);
        assertNotEquals(0, commitsTimeline.size());
        assertNotEquals(0, commitsTimeline.get("2021-03-11").intValue());
    }
}
