package com.kata.preproject.PP_3_1_4_SpringBootSecurityBootstrap.dao;

import com.kata.preproject.PP_3_1_4_SpringBootSecurityBootstrap.models.User;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class)
                .setParameter("id", id).getSingleResult();
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public void delete(Long id) {
        if (getUserById(id) != null) {
            entityManager.remove(getUserById(id));
        }
    }

    @Override
    public User getUserByUsername(String username) {
        return entityManager.createQuery("select u from User u left join fetch u.roles where u.userName=:username", User.class)
                .setParameter("username", username).getSingleResult();
    }
}







