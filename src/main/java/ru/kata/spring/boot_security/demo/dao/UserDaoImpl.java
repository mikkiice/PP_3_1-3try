package ru.kata.spring.boot_security.demo.dao;


import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {


    @PersistenceContext
    private EntityManager entityManager;

    private final UserRepository userRepository;

    public UserDaoImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Override
    @Transactional
    public void updateUser(User user) {
            entityManager.merge(user);
    }

    @Override
    @Transactional
    public void deleteByUsername(String username) {
        User user = getUserByUsername(username);
        if (user != null) {
            entityManager.remove(user);
        }
    }


    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public String getRolesByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user.getRole();
    }

    @Override
    public User getUserByUsername(String username) {
        List<User> users = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getResultList();
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }
}
