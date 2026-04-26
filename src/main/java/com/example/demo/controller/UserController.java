package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 登録画面表示
    @GetMapping("/register")
    public String showForm() {
        return "register";
    }

    // 登録処理
    @PostMapping("/register")
    public String register(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        userService.register(user);

        return "redirect:/register";
    }

    // 一覧表示
    @GetMapping("/users")
    public String list(Model model, HttpSession session) {

        if (session.getAttribute("loginUser") == null) {
            return "redirect:/login";
        }

        model.addAttribute("users", userService.getUsers());
        return "users";
    }

    // ログイン
    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            Model model,
            HttpSession session
    ) {
        User user = userService.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("loginUser", user);
            return "redirect:/users";
        } else {
            model.addAttribute("error", "ログイン失敗");
            return "login";
        }
    }

    // ログアウト
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // セッション削除
        return "redirect:/login";
    }
}
