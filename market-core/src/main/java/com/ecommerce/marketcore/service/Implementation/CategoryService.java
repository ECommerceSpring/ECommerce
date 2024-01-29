package com.ecommerce.marketcore.service.Implementation;

import com.ecommerce.marketcore.model.Category;
import com.ecommerce.marketcore.repository.CategoryRepository;
import com.ecommerce.marketcore.service.Interface.ICategoryService;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;
    private final Validator categoryValidator;

    public CategoryService(CategoryRepository categoryRepository, Validator categoryValidator)
    {
        this.categoryRepository = categoryRepository;
        this.categoryValidator = categoryValidator;
    }

    public List<Category> getAllMainCategories()
    {
        return categoryRepository.findAllByParentIdIsNullOrParentIdIs(0);
    }

    public List<Category> getAllByParentId(long parentCategoryId)
    {
        return categoryRepository.findAllByParentId(parentCategoryId);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public Category update(Category category)
    {
        // check what to do (either get it by a native query or using entity manager)
        return categoryRepository.save(category);
    }
}
