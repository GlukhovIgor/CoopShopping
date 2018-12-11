package com.coopshopping.service;

import com.coopshopping.model.Category;
import com.coopshopping.model.Item;
import com.coopshopping.model.User;

import java.util.List;

public interface ItemService {

    Item findById(int itemid);

    List<Item> findAll();

    //int countItems();

    void addItem(String name, String desc, int categoryid);

    void updateItem(Item item, User user);

    void deleteItem(int id);

    void addRequest(Item item, User user);
}