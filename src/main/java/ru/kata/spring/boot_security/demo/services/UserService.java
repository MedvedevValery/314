package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    void save(User user);
    void deleteUser(Long id);
    List<User> getAllUsers();
    User loadUserByUsername(String username);
    User findUserById(Long id);
}
