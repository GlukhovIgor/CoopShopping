package com.coopshopping.repository;

import com.coopshopping.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByUserid(int userid);
}
