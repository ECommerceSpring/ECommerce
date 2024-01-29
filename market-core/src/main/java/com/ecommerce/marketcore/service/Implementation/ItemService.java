package com.ecommerce.marketcore.service.Implementation;

import com.ecommerce.marketcore.model.Item;
import com.ecommerce.marketcore.model.Rating;
import com.ecommerce.marketcore.repository.ItemRepository;
import com.ecommerce.marketcore.repository.RatingRepository;
import com.ecommerce.marketcore.service.Interface.IItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService implements IItemService {
    private final ItemRepository itemRepository;
    private final RatingRepository ratingRepository;

    public ItemService(ItemRepository itemRepository, RatingRepository ratingRepository)
    {
        this.itemRepository = itemRepository;
        this.ratingRepository = ratingRepository;
    }

    public Item getById(long id) {
        return itemRepository.getById(id);
    }

    public List<Item> getAllByCategories(List<Long> categoryIds)
    {
        return itemRepository.getAllByCategoryIdIn(categoryIds);
    }

    public Item save(Item item, List<Long> CategoryIds) {
        // todo
        return itemRepository.save(item);
    }

    public Item update(Item item, List<Long> CategoryIds) {
        // todo
        return itemRepository.save(item);
    }

    public Rating saveRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    public Rating updateRating(Rating rating) {
        // todo
        return ratingRepository.save(rating);
    }
}
