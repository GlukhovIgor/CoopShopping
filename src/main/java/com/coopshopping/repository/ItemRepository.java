package com.coopshopping.repository;

import com.coopshopping.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    Item findByItemid(int itemid);
    Item findByName(String name);
}
