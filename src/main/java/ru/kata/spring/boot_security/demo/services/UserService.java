package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.Collection;
import java.util.List;

public interface UserService extends UserDetailsService {
    @Override
    @Transactional
    UserDetails loadUserByUsername(String username);
    User findByUsername(String username);
    Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles);
    void saveUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);
    User findById(Long id);
    String getRoleNamesByUsername(String username);
    List<User> findAllUsers();
    void deleteByUsername(String username);
    boolean existsByUsername(String username);
}
