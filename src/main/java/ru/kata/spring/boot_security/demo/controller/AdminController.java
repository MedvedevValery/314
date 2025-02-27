package ru.kata.spring.boot_security.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    private final RoleService roleService;

    public AdminController(UserService userService
        , RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;

    }

    @GetMapping
    public String showAllUsers(Model model, Principal principal) {
        User user = userService.loadUserByUsername(principal.getName());
        User newUser = new User();
        List<User> users = userService.getAllUsers();
        model.addAttribute("allUsers", users);
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("user", user);
        model.addAttribute("newUser", newUser);

        return "/admin/all_users_view";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam("userId") Long id) {
        userService.deleteUser(id);

        return "redirect:/admin";
    }
}
