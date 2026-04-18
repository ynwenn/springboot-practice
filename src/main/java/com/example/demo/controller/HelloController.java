package com.example.demo.controller;

import com.example.demo.entity.Name;
import com.example.demo.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @Autowired
    private HelloService helloService;

    // 一覧表示
    @GetMapping("/hello")
    public String hello(Model model) {

        model.addAttribute("names", helloService.getNames());

        return "hello";
    }

    // 追加
    @PostMapping("/hello")
    public String helloPost(@RequestParam("name") String name, Model model) {

        helloService.addName(name);

        model.addAttribute("names", helloService.getNames());

        return "redirect:/hello";
    }

    // 削除
    @PostMapping("/delete")
    public String delete(@RequestParam("id") Long id, Model model) {

        helloService.deleteName(id);

        model.addAttribute("names", helloService.getNames());

        return "redirect:/hello";
    }

    // 編集画面
    @GetMapping("/edit")
    public String edit(@RequestParam("id") Long id, Model model) {

        Name name = helloService.findById(id);

        model.addAttribute("name", name);

        return "edit";
    }

    // 更新
    @PostMapping("/update")
    public String update(@RequestParam("id") Long id,
                         @RequestParam("name") String name) {

        helloService.updateName(id, name);

        return "redirect:/hello";
    }
}
