package org.example.module3.hibernate.dao.interfaces;

import org.example.module3.hibernate.entity.User;

public interface UserDao {
    void update(User user);
    User findUserById(Long id);
    void delete(User user);
}
