package com.example.repoanalysis.services;

import com.example.repoanalysis.entities.Results;
import com.example.repoanalysis.entities.SearchRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SearchServiceTests {

    @Autowired
    private SearchService searchService;

    @Test
    public void testIfPerPageLimitIsGiven_SearchItemsEqualLimit(){
        SearchRequest request = new SearchRequest();
        request.setQuery("mono");
        request.setResultsPerPage(5);

        Results result = searchService.getSearchResults(request);
        assertEquals(5, result.getItems().size());
    }
}
