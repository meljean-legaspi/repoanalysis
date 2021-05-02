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
    private StringBuilder stringBuilder;

    @Autowired
    public RepoInfoService(){
        WebClient.Builder builder = WebClient.builder();
        webClient = builder.baseUrl("https://api.github.com").build();
    }

    public List<Contributor> getContributors(RepoInfoRequest repoInfoRequest){
        stringBuilder = new StringBuilder();
        stringBuilder.append("/repos/");
        stringBuilder.append(repoInfoRequest.getRepoFullName());
        stringBuilder.append("/contributors");
        Flux<Contributor> contributorFlux = webClient.get()
                .uri(uriBuilder -> uriBuilder.path(stringBuilder.toString())
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
        stringBuilder = new StringBuilder();
        stringBuilder.append("/repos/");
        stringBuilder.append(repoInfoRequest.getRepoFullName());
        stringBuilder.append("/commits");
        Flux<Commit> commitFlux = webClient.get()
                .uri(uriBuilder -> uriBuilder.path(stringBuilder.toString())
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
        return commits.stream()
                .filter(c -> c.getAuthor() != null)
                .map(c -> c.getAuthor().getUsername())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public Map<String, Long> getCommitsTimeline(RepoInfoRequest repoInfoRequest) {
        List<Commit> commits = getLatestCommits(repoInfoRequest);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        return new TreeMap<>(commits.stream()
                .map(c -> dateFormatter.format(c.getDate()))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
    }
}
