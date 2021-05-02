package com.example.repoanalysis.services;

import com.example.repoanalysis.entities.SearchResultBookmark;
import com.example.repoanalysis.repositories.BookmarkRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookmarkService {
    private final BookmarkRepository repository;

    public BookmarkService(BookmarkRepository repository) {
        this.repository = repository;
    }

    public void addBookmark(SearchResultBookmark bookmark){
        repository.insert(bookmark);
    }

    public SearchResultBookmark getBookmark(String query){
        return repository.findSearchResultBookmarkBy(query);
    }

    public List<SearchResultBookmark> getBookmarks(){
        return repository.findAll();
    }

    public void deleteAll(){
        repository.deleteAll();
    }
}
