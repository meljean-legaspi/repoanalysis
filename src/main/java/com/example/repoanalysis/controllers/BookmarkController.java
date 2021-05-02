package com.example.repoanalysis.controllers;

import com.example.repoanalysis.entities.SearchResultBookmark;
import com.example.repoanalysis.services.BookmarkService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookmarkController {
    private BookmarkService bookmarkService;

    public BookmarkController(BookmarkService bookmarkService){
        this.bookmarkService = bookmarkService;
    }

    @GetMapping("/bookmarks")
    public List<SearchResultBookmark> getBookmarks(){
        return bookmarkService.getBookmarks();
    }

    @PostMapping("/bookmarks/add")
    public String addBookmark(@RequestBody SearchResultBookmark bookmark){
        bookmarkService.addBookmark(bookmark);
        return "Bookmarked!";
    }
}
