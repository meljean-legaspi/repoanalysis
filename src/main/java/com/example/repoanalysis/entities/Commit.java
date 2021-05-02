package com.example.repoanalysis.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Data
@NoArgsConstructor
public class Commit {
    @JsonProperty("author")
    private Contributor author;
    @SuppressWarnings("SpellCheckingInspection")
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Date date;

    @SuppressWarnings("unchecked")
    @JsonProperty("commit")
    private void unpackFromCommitProperty(Map<String,Object> commit) throws ParseException {
        Map<String,String> author = (Map<String,String>)commit.get("author");
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        date = dateFormatter.parse(author.get("date"));
    }
}
