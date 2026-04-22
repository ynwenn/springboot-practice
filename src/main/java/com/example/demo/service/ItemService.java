package com.example.demo.service;

import com.example.demo.entity.Item;
import com.example.demo.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void addItem(Item item) {
        itemRepository.save(item);
    }

    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    public Item findById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    public void updateItem(Long id, String item) {
        Item itemEntity = itemRepository.findById(id).orElse(null);
        if (itemEntity != null) {
            itemEntity.setItem(item);
            itemRepository.save(itemEntity);
        }
    }
}
