package com.example.repoanalysis.utilities;

import com.example.repoanalysis.entities.Pagination;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaginatorTests {
    @Test
    public void testComputeTotalPagesWithEvenTotalResults_ShouldReturnTotalPages(){
        Paginator paginator = new Paginator();
        int totalResults = 100;
        int pageSize = 10;
        int currentPage = 1;
        Pagination pagination = paginator.getPagination(totalResults, pageSize, currentPage);
        assertEquals(10, pagination.getTotalPages());
    }

    @Test
    public void testComputeTotalPagesWithOddTotalResults_ShouldReturnTotalPages(){
        Paginator paginator = new Paginator();
        int totalResults = 105;
        int pageSize = 10;
        int currentPage = 1;
        Pagination pagination = paginator.getPagination(totalResults, pageSize, currentPage);
        assertEquals(11, pagination.getTotalPages());
    }
}
