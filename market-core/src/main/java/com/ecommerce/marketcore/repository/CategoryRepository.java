package com.ecommerce.marketcore.repository;

import com.ecommerce.marketcore.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByParentIdIsNullOrParentIdIs(long parentId);

    List<Category> findAllByParentId(long parentId);
}