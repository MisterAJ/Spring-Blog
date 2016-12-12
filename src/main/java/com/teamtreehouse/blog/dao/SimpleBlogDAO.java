package com.teamtreehouse.blog.dao;


import com.teamtreehouse.blog.model.BlogEntry;
import com.teamtreehouse.blog.model.Comment;
import com.teamtreehouse.blog.model.NotFoundException;

import java.util.ArrayList;
import java.util.List;

public class SimpleBlogDAO implements BlogDao {
    private List<BlogEntry> blogEntries;

    public SimpleBlogDAO() {
        blogEntries = new ArrayList<>();
    }

    @Override
    public boolean addEntry(BlogEntry blogEntry) {
        return blogEntries.add(blogEntry);
    }

    @Override
    public boolean removeEntry(BlogEntry blogEntry) {
        return blogEntries.remove(blogEntry);
    }

    @Override
    public boolean editEntry(BlogEntry blogEntry) {
        blogEntries.remove(blogEntry);
        return blogEntries.add(blogEntry);
    }

    @Override
    public void addComment(BlogEntry blogEntry, Comment comment) {
        blogEntry.addComment(comment);
    }

    @Override
    public List<BlogEntry> findAllEntries() {
        return new ArrayList<>(blogEntries);
    }

    @Override
    public BlogEntry findEntryBySlug(String slug) {
        return blogEntries.stream()
                .filter(BlogEntry -> BlogEntry.getSlug().equals(slug))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }
}
