package com.teamtreehouse.blog.model;

import com.github.slugify.Slugify;

import java.util.*;

public class BlogEntry {
    private String slug;
    private String title;
    private String creator;
    private List<Comment> comments = new ArrayList<>();
    private Set<String> voters;
    private String body;
    private boolean userCheck;
    private Date dateCreated = new Date();


    public BlogEntry(String title, String creator, String body) {
        voters = new HashSet<>();
        this.title = title;
        this.creator = creator;
        this.body = body;
        this.dateCreated = new Date();
        Slugify slugify = new Slugify();
        slug = slugify.slugify(title);
    }

    public String getTitle() {
        return title;
    }

    public String getCreator() {
        return creator;
    }



    public boolean addComment(Comment comment) {
        comments.add(comment);
        return false;
    }

    public List<Comment> getComments(){
        return comments;
    }

    public List<String> getVoters() {
        return new ArrayList<>(voters);
    }

    public boolean addVoter(String voterUserName) {
        return voters.add(voterUserName);
    }

    public int getVoteCount() {
        return voters.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlogEntry blogEntry = (BlogEntry) o;

        if (title != null ? !title.equals(blogEntry.title) : blogEntry.title != null) return false;
        return creator != null ? creator.equals(blogEntry.creator) : blogEntry.creator == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        return result;
    }

    public String getSlug() {
        return slug;
    }

    public String getBody() {
        return body;
    }

    public boolean isUserCheck(String user) {
        return creator.equalsIgnoreCase(user);
    }

    public Date getDateCreated() {
        return dateCreated;
    }
}
