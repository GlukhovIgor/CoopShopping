package com.coopshopping.service;

import com.coopshopping.model.Role;
import com.coopshopping.model.User;
import com.coopshopping.model.UserRole;
import com.coopshopping.repository.RoleRepository;
import com.coopshopping.repository.UserRepository;
import com.coopshopping.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<UserRole> userRoles = new HashSet<>();
        for (Role role: roleRepository.findAll()){
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getUserid());
            userRole.setRoleId(role.getRoleid());
            userRole.setRoleByRoleId(role);
            userRole.setUserByUserId(user);
            userRoles.add(userRole);
        }
        user.setUserRolesByUserid(userRoles);
        //user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
        UserRole userRole = new UserRole();
        userRole.setUserId(userRepository.findByUsername(user.getUsername()).getUserid());
        userRole.setRoleId(1);
        userRoleRepository.save(userRole);
        /*for(UserRole userRole: userRoles){
            userRoleRepository.save(userRole);
        }*/
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findById(int id) {
        return userRepository.findByUserid(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }


}
