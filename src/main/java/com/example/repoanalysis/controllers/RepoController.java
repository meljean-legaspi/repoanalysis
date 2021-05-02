package com.example.repoanalysis.controllers;

import com.example.repoanalysis.entities.RepoInfoRequest;
import com.example.repoanalysis.services.RepoInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RepoController {
    private final RepoInfoService repoInfoService;

    public RepoController(RepoInfoService repoInfoService){
        this.repoInfoService = repoInfoService;
    }

    @GetMapping("/repo/contributors")
    public String getRepoContributors(Model model,
                                      @RequestParam String name,
                                      @RequestParam(defaultValue = "20") int limit,
                                      @RequestParam(defaultValue = "1") int page){
        RepoInfoRequest repoInfoRequest = new RepoInfoRequest();
        repoInfoRequest.setRepoFullName(name);
        repoInfoRequest.setResultsPerPage(limit);
        repoInfoRequest.setPage(page);

        model.addAttribute("repoName", name);
        model.addAttribute("users", repoInfoService.getContributors(repoInfoRequest));
        return "users";
    }

    @GetMapping("/repo/commits/stats")
    public String getContributorCommitStats(Model model,
                                            @RequestParam String name){
        RepoInfoRequest repoInfoRequest = new RepoInfoRequest();
        repoInfoRequest.setRepoFullName(name);

        model.addAttribute("repoName", name);
        model.addAttribute("users", repoInfoService.getCommitsPerUser(repoInfoRequest));
        model.addAttribute("timeline", repoInfoService.getCommitsTimeline(repoInfoRequest));
        return "commitstats";
    }
}
