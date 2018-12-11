package com.coopshopping.repository;

import com.coopshopping.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByCategoryid(int categoryid);
}
