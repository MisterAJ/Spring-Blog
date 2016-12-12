package com.teamtreehouse.blog.dao;

import com.teamtreehouse.blog.model.BlogEntry;
import com.teamtreehouse.blog.model.Comment;

import java.util.List;

public interface BlogDao {
    boolean addEntry(BlogEntry blogEntry);

    void addComment(BlogEntry blogEntry, Comment comment);

    List<BlogEntry> findAllEntries();

    BlogEntry findEntryBySlug(String slug);

    boolean editEntry(BlogEntry blogEntry);

    boolean removeEntry(BlogEntry blogEntry);
}
