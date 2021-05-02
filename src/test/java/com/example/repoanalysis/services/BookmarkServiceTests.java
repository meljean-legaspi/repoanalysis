package com.example.repoanalysis.services;

import com.example.repoanalysis.entities.SearchResultBookmark;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class BookmarkServiceTests {

    @Autowired
    private BookmarkService bookmarkService;

    @Test
    public void testWhenBookmarkIsAdded_ShouldBeFoundInDatabase(){
        String query = "mono";
        SearchResultBookmark bookmark = new SearchResultBookmark();
        bookmark.setQuery(query);
        bookmark.setPage(1);
        bookmarkService.addBookmark(bookmark);

        SearchResultBookmark bookmarkFromDb = bookmarkService.getBookmark(query);
        assertEquals(bookmark.getQuery(), bookmarkFromDb.getQuery());
    }

    @Test
    public void testGetAllBookmarks_ShouldReturnAllBookmarks(){
        String query1 = "mono";
        SearchResultBookmark bookmark1 = new SearchResultBookmark();
        bookmark1.setQuery(query1);
        bookmark1.setPage(1);
        bookmarkService.addBookmark(bookmark1);

        String query2 = "nuget";
        SearchResultBookmark bookmark2 = new SearchResultBookmark();
        bookmark2.setQuery(query2);
        bookmark2.setPage(1);
        bookmarkService.addBookmark(bookmark2);

        List<SearchResultBookmark> bookmarksFromDb = bookmarkService.getBookmarks();
        assertNotEquals(0, bookmarksFromDb.size());
    }

    @AfterEach
    public void tearDown(){
        bookmarkService.deleteAll();
    }
}
