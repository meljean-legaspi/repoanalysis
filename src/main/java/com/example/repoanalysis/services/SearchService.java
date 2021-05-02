package com.example.repoanalysis.services;

import com.example.repoanalysis.entities.Results;
import com.example.repoanalysis.entities.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class SearchService {

    private final WebClient webClient;
    private final WebClient.Builder builder;

    @Autowired
    public SearchService(){
        builder = WebClient.builder();
        webClient = builder.baseUrl("https://api.github.com").build();
    }

    public Results getSearchResults(SearchRequest searchRequest) throws NullPointerException {
        String path = "/search/repositories";
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path(path)
                        .queryParam("q", searchRequest.getQuery())
                        .queryParam("page", String.valueOf(searchRequest.getPage()))
                        .queryParam("per_page", String.valueOf(searchRequest.getResultsPerPage()))
                        .build())
                .header("Accept", "application/vnd.github.v3+json")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(Results.class)
                .block()
                .getBody();
    }
}
