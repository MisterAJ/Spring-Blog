package com.teamtreehouse.blog.model;

import java.util.HashMap;
import java.util.Map;

public class Comment {
    String creator;
    String comment;

    public Comment(String creator, String comment) {
        this.creator = creator;
        this.comment = comment;
    }
}
