package com.teamtreehouse.blog;

import com.teamtreehouse.blog.dao.BlogDao;
import com.teamtreehouse.blog.dao.SimpleBlogDAO;
import com.teamtreehouse.blog.model.BlogEntry;
import com.teamtreehouse.blog.model.Comment;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        staticFileLocation("/public");

        BlogDao dao = new SimpleBlogDAO();

        before((request, response) -> {
            if (request.cookie("username") != null) {
                request.attribute("username", request.cookie("username"));
            }
        });

        before("blog", (request, response) -> {
            // TODO - Make redirect message
            if (request.attribute("username") == null) {
                response.redirect("/");
                halt();
            }
        });
        // TODO - Post comments

        // Main Page

        get("/sign-in", (request, response) -> {
            Map<String, String> user = new HashMap<>();
            user.put("username", request.attribute("username"));
            return new ModelAndView(user, "sign-in.hbs");
        }, new HandlebarsTemplateEngine());

        get("/blog", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("blogEntry", dao.findAllEntries());
            return new ModelAndView(model, "blog.hbs");
        }, new HandlebarsTemplateEngine());

        post("/blog", (request, response) -> {
            String title = request.queryParams("title");
            String blog = request.queryParams("blogBody");
            String creator = request.queryParams("username");
            BlogEntry blogEntry = new BlogEntry(title, creator, blog);
            dao.addEntry(blogEntry);
            response.redirect("/blog");
            return null;
        });

        // Blog Pages

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("blogEntry", dao.findAllEntries());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/detail/:slug", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("blogEntry", dao.findEntryBySlug(request.params("slug")));
            return new ModelAndView(model, "detail.hbs");
        }, new HandlebarsTemplateEngine());

        get("/new", (request, response) -> {
            return new ModelAndView(null, "new.hbs");
        }, new HandlebarsTemplateEngine());

        // Blog Changes

        get("/blog/:slug/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("blogEntry", dao.findEntryBySlug(request.params("slug")));
            return new ModelAndView(model, "edit.hbs");
        }, new HandlebarsTemplateEngine());

        post("/blog/:slug/edit", (request, response) -> {
            String title = request.queryParams("title");
            String blog = request.queryParams("blogBody");
            String creator = request.queryParams("username");
            BlogEntry blogEntry = new BlogEntry(title, creator, blog);
            dao.editEntry(blogEntry);
            response.redirect("/blog");
            return null;
        });

        post("/blog/:slug/delete", (request, response) -> {
            BlogEntry blogEntry = dao.findEntryBySlug(request.params("slug"));
            dao.removeEntry(blogEntry);
            response.redirect("/blog");
            return null;
        });

        post("/blog/:slug/vote", (request, response) -> {
            BlogEntry blogEntry = dao.findEntryBySlug(request.params("slug"));
            blogEntry.addVoter(request.attribute("username"));
            response.redirect("/blog");
            return null;
        });

        post("/blog/:slug/comment", (request, response) -> {
            BlogEntry blogEntry = dao.findEntryBySlug(request.params("slug"));
            String user = request.queryParams("username");
            String newComment = request.queryParams("comment");
            Comment comment = new Comment(user, newComment);
            dao.addComment(blogEntry, comment);
            response.redirect("/blog/:slug/detail");
            return null;
        });

        // Sign in

        post("/sign-in", (request, response) -> {
            Map<String, String> model = new HashMap<>();
            String username = request.queryParams("username");
            response.cookie("username", username);
            model.put("username", username);
            return new ModelAndView(model, "sign-in.hbs");
        }, new HandlebarsTemplateEngine());

        post("/password.hbs", (request, response) -> {
            Map<String, String> model = new HashMap<>();
            String password = request.queryParams("password");
            model.put("password", password);
            return new ModelAndView(model, "password.hbs");
        }, new HandlebarsTemplateEngine());

        get("/d1", (request, response) -> {
            return new ModelAndView(null, "d1.hbs");
        }, new HandlebarsTemplateEngine());

        get("/d2", (request, response) -> {
            return new ModelAndView(null, "d2.hbs");
        }, new HandlebarsTemplateEngine());

        get("/d3", (request, response) -> {
            return new ModelAndView(null, "d3.hbs");
        }, new HandlebarsTemplateEngine());

        get("/d4", (request, response) -> {
            return new ModelAndView(null, "d4.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
