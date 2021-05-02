package com.example.repoanalysis.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepoInfoRequest {
    private String repoFullName;
    private int resultsPerPage;
    private int page;
}