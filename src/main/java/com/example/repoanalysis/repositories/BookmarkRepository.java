package com.example.repoanalysis.repositories;

import com.example.repoanalysis.entities.SearchResultBookmark;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkRepository extends MongoRepository<SearchResultBookmark, String> {
    SearchResultBookmark findSearchResultBookmarkBy(String query);
}