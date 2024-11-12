package ru.kata.spring.boot_security.demo.dao;


import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

@Repository
public interface UserDao {
    void saveUser(User user);
    void deleteUser(Long id);
    void updateUser(User user);
    void deleteByUsername(String username);
    List<User> getAllUsers();
    String getRolesByUsername(String username);
    User getUserByUsername(String username);
    User getUserById(Long id);
}
