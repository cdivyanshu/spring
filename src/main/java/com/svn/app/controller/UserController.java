package com.svn.app.controller;

import com.svn.app.model.User;
import com.svn.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // Display list of users
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listUsers", userService.getAllUsers());
        return "index";
    }

    @GetMapping("/showNewUserForm")
    public String showNewUserForm(Model model) {
        // create model attribute to bind form data
        User user = new User();
        model.addAttribute("user", user);
        return "add-user";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        // save user to database
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") Long id, Model model) {
        // get user from the service
        User user = userService.getUserById(id);
        // set user as a model attribute to pre-populate the form
        model.addAttribute("user", user);
        return "add-user";
    }

    @GetMapping("/viewUser/{id}")
    public String viewUser(@PathVariable(value = "id") Long id, Model model) {
        // get user from the service
        User user = userService.getUserById(id);
        // set user as a model attribute to show the details
        model.addAttribute("user", user);
        return "view-user";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(value = "id") Long id) {
        // call delete user method
        this.userService.deleteUserById(id);
        return "redirect:/";
    }
}
