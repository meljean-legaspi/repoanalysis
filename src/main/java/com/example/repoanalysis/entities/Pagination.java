package com.example.repoanalysis.entities;

import lombok.Data;

@Data
public class Pagination {
    private int totalPages;
    private int pageSize;
    private int currentPage;
    private int lastPage;
}
