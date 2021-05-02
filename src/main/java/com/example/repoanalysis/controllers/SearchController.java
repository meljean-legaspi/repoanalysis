package com.example.repoanalysis.controllers;

import com.example.repoanalysis.entities.Results;
import com.example.repoanalysis.entities.SearchRequest;
import com.example.repoanalysis.services.SearchService;
import com.example.repoanalysis.utilities.Paginator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SuppressWarnings("SameReturnValue")
@Controller
public class SearchController {
    private final SearchService searchService;
    public SearchController(SearchService searchService){
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public String searchRepos(Model model,
                              @RequestParam String query,
                              @RequestParam(defaultValue = "20") int limit,
                              @RequestParam(defaultValue = "1") int page) {
        SearchRequest request = new SearchRequest();
        request.setQuery(query);
        request.setResultsPerPage(limit);
        request.setPage(page);
        model.addAttribute("query", query);
        Results results = searchService.getSearchResults(request);
        model.addAttribute("results", results);
        Paginator paginator = new Paginator();
        model.addAttribute("pagination", paginator.getPagination(results.getTotalCount(), limit, page));
        return "results";
    }
}
