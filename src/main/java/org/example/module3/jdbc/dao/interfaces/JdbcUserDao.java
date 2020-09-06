package org.example.module3.jdbc.dao.interfaces;

import org.example.module3.jdbc.entity.User;

import java.sql.Connection;

public interface JdbcUserDao {
    User findUserById(Long userId, Connection connection);
}
