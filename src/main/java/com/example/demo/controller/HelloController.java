package com.example.demo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.service.HelloService;

@Controller
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/hello")
    public String hello(Model model) {

        model.addAttribute("names", helloService.getNames());

        return "hello";
    }

    @PostMapping("/hello")
    public String helloPost(@RequestParam("name") String name, Model model) {

        helloService.addName(name);

        model.addAttribute("names", helloService.getNames());

        return "redirect:/hello";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") Long id, Model model) {

        helloService.deleteName(id);

        model.addAttribute("names", helloService.getNames());

        return "redirect:/hello";
    }
}
