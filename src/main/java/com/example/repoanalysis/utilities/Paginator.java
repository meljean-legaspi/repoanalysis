package com.example.repoanalysis.utilities;

import com.example.repoanalysis.entities.Pagination;

public class Paginator {
    public Pagination getPagination(int totalResults, int pageSize, int currentPage) {
        Pagination pagination = new Pagination();
        int totalPages = computeTotalPages(totalResults, pageSize);
        pagination.setTotalPages(totalPages);
        pagination.setLastPage(totalPages);
        pagination.setPageSize(pageSize);
        pagination.setCurrentPage(currentPage);
        return pagination;
    }

    private int computeTotalPages(int totalResults, int perPage){
        if (totalResults > 1000) {
            totalResults = 1000;
        }

        boolean hasRemainder = totalResults % perPage > 0;
        int totalPages = totalResults / perPage;
        if (hasRemainder)
            totalPages += 1;
        return totalPages;
    }
}
