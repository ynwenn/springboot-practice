package com.example.demo.controller;

import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@Controller
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public String list(Model model, HttpSession session) {

        model.addAttribute("posts", postService.getPosts());

        model.addAttribute("loginUser", session.getAttribute("loginUser"));

        return "posts";
    }

    // 投稿
    @PostMapping("/posts")
    public String create(
            @RequestParam("content") String content,
            HttpSession session
    ) {
        User loginUser = (User) session.getAttribute("loginUser");

        Post post = new Post();
        post.setContent(content);

        post.setUser(loginUser);

        postService.addPost(post);

        return "redirect:/posts";
    }

    // 削除
    @PostMapping("/deletePost")
    public String delete(@RequestParam("id") Long id) {

        postService.deletePost(id);

        return "redirect:/posts";
    }
}
