package com.ecommerce.marketcore.service.Interface;

import com.ecommerce.marketcore.model.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> getAllMainCategories();
    
    List<Category> getAllByParentId(long parentCategoryId);

    Category save(Category category);

    Category update(Category category);
}
