package com.ecommerce.marketcore.service.Interface;

import com.ecommerce.marketcore.model.Item;
import com.ecommerce.marketcore.model.Rating;

import java.util.List;

public interface IItemService {
    Item getById(long id);

    List<Item> getAllByCategories(List<Long> categoryIds);

    Item save(Item item, List<Long> categoryIds);

    Item update(Item item, List<Long> categoryIds);

    Rating saveRating(Rating rating);

    Rating updateRating(Rating rating);
}
