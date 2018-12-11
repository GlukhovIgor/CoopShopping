package com.coopshopping.service;

import com.coopshopping.model.UserRole;
import com.coopshopping.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    UserRoleRepository userRoleRepository;

    @Override
    public void save(UserRole userRole) {
        userRoleRepository.save(userRole);
    }
}
