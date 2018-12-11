package com.coopshopping.controller;

import com.coopshopping.model.User;
import com.coopshopping.service.SecurityService;
import com.coopshopping.service.UserService;
import com.coopshopping.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("thisid")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        model.addAttribute("name", SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("thisid",userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getUserid());
        return "welcome";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getAllUsers(Model model){
        model.addAttribute("users", userService.findAll());
        return "userslist";
    }

    @RequestMapping("/user/{id}")
    public String getById(@PathVariable("id") int id, ModelMap model){
        model.addAttribute("user", userService.findById(id));
        return "showuser";
    }
}
