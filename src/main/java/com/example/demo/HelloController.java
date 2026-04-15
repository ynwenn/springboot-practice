package com.example.demo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("/hello")
    public String helloPost(@RequestParam("name") String name, Model model) {

        String message = "こんにちは、" + name + "さん！";

        model.addAttribute("message", message);

        return "hello";
    }
}
