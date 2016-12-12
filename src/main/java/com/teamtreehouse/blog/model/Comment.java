package com.teamtreehouse.blog.model;

import java.util.HashMap;
import java.util.Map;

public class Comment {
    private String creator;
    private String comment;

    public Comment(String creator, String comment) {
        this.creator = creator;
        this.comment = comment;
    }

    public String getComment(){
        return comment;
    }

    public String getCreator(){
        return creator;
    }
}
