package com.example.repoanalysis.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Contributor {
    @JsonProperty("login")
    private String username;
    @JsonProperty("html_url")
    private String githubUrl;
}