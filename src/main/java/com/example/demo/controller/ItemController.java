package com.example.demo.controller;

import com.example.demo.entity.Item;
import com.example.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    // 一覧表示
    @GetMapping("/items")
    public String list(Model model) {

        model.addAttribute("items", itemService.getItems());

        model.addAttribute("item", new Item());

        return "list";
    }

    // 追加
    @PostMapping("/items")
    public String create(@RequestParam("item") String item) {

        Item newItem = new Item();
        newItem.setItem(item);

        itemService.addItem(newItem);

        return "redirect:/items";
    }
    // 削除
    @PostMapping("/delete")
    public String delete(@RequestParam("id") Long id) {

        itemService.deleteItem(id);

        return "redirect:/items";
    }

    // 編集画面
    @GetMapping("/edit")
    public String edit(@RequestParam("id") Long id, Model model) {

        Item item = itemService.findById(id);

        model.addAttribute("item", item);

        return "edit";
    }

    // 更新
    @PostMapping("/update")
    public String update(@RequestParam("id") Long id,
                         @RequestParam("newItem") String newItem) {

        itemService.updateItem(id, newItem);

        return "redirect:/items";
    }

}