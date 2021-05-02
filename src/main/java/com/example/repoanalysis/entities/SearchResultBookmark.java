package com.example.repoanalysis.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "bookmarks")
public class SearchResultBookmark {
    @Id
    private String id;
    private String query;
    private int page;

    @Override
    public String toString(){
        return String.format("Bookmark {query = %s, page = %d} ", query, page);
    }
}
