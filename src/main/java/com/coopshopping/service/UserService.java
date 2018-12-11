package com.coopshopping.service;

import com.coopshopping.model.User;

import java.util.List;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

    User findById(int userid);

    List<User> findAll();

}
