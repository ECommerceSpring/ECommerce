package com.ecommerce.marketcore.controller;

import com.ecommerce.marketcore.model.Item;
import com.ecommerce.marketcore.model.Rating;
import com.ecommerce.marketcore.service.Interface.IItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {
    private final IItemService itemService;

    public ItemController(IItemService itemService)
    {
        this.itemService = itemService;
    }

    @GetMapping("/Item")
    public Item getById(Long id) {
        return itemService.getById(id);
    }

    @GetMapping("/Item/All/ByCategories")
    public List<Item> getAllByCategories(List<Long> categoryIds) {
        return itemService.getAllByCategories(categoryIds);
    }

    @PostMapping("/Item")
    public Item save(Item item, List<Long> categoryIds) {
        return itemService.save(item, categoryIds);
    }

    @PutMapping("/Item")
    public Item update(Item item, List<Long> CategoryIds) {
        return itemService.update(item, CategoryIds);
    }

    @PostMapping("/Item/Rating")
    public Rating save(Rating rating) {
        return itemService.saveRating(rating);
    }

    @PutMapping("/Item/Rating")
    public Rating update(Rating rating) {
        return itemService.updateRating(rating);
    }
}
