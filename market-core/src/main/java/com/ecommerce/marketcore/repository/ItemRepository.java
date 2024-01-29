package com.ecommerce.marketcore.repository;

import com.ecommerce.marketcore.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    // todo make native query
    List<Item> getAllByCategoryIdIn(List<Long> categoryIds);
}

