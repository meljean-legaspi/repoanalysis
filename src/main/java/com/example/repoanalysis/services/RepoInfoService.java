package com.example.repoanalysis.services;

import com.example.repoanalysis.entities.Commit;
import com.example.repoanalysis.entities.Contributor;
import com.example.repoanalysis.entities.RepoInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RepoInfoService {

    private final WebClient webClient;
    private final WebClient.Builder builder;

    @Autowired
    public RepoInfoService(){
        builder = WebClient.builder();
        webClient = builder.baseUrl("https://api.github.com").build();
    }

    public List<Contributor> getContributors(RepoInfoRequest repoInfoRequest){
        StringBuilder string = new StringBuilder();
        string.append("/repos/");
        string.append(repoInfoRequest.getRepoFullName());
        string.append("/contributors");
        Flux<Contributor> contributorFlux = webClient.get()
                .uri(uriBuilder -> uriBuilder.path(string.toString())
                        .queryParam("page", String.valueOf(repoInfoRequest.getPage()))
                        .queryParam("per_page", String.valueOf(repoInfoRequest.getResultsPerPage()))
                        .build())
                .header("Accept", "application/vnd.github.v3+json")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Contributor.class);
        return contributorFlux.collectList().block();
    }

    private List<Commit> getLatestCommits(RepoInfoRequest repoInfoRequest){
        StringBuilder string = new StringBuilder();
        string.append("/repos/");
        string.append(repoInfoRequest.getRepoFullName());
        string.append("/commits");
        Flux<Commit> commitFlux = webClient.get()
                .uri(uriBuilder -> uriBuilder.path(string.toString())
                        .queryParam("per_page", "100")
                        .build())
                .header("Accept", "application/vnd.github.v3+json")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Commit.class);
        return commitFlux.collectList().block();
    }

    public Map<String, Long> getCommitsPerUser(RepoInfoRequest repoInfoRequest){
        List<Commit> commits = getLatestCommits(repoInfoRequest);
        Map<String, Long> commitsPerUser = commits.stream()
                .filter(c -> c.getAuthor() != null)
                .map(c -> c.getAuthor().getUsername())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return commitsPerUser;
    }

    public Map<String, Long> getCommitsTimeline(RepoInfoRequest repoInfoRequest) {
        List<Commit> commits = getLatestCommits(repoInfoRequest);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, Long> commitsTimeline = new TreeMap<>(commits.stream()
                .map(c -> dateFormatter.format(c.getDate()))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
        return commitsTimeline;
    }
}
