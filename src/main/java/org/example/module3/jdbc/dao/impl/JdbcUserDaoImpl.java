package org.example.module3.jdbc.dao.impl;

import org.example.module3.jdbc.dao.interfaces.JdbcUserDao;
import org.example.module3.jdbc.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUserDaoImpl implements JdbcUserDao {
    @Override
    public User findUserById(Long userId, Connection connection) {

        User user = new User();

        try (PreparedStatement getUser = connection.prepareStatement(
                "SELECT * FROM users WHERE id = ?")) {

            getUser.setLong(1, userId);

            ResultSet resultSet = getUser.executeQuery();

            while (resultSet.next()) {

                String phoneNumber = resultSet.getString("phone_number");
                String name = resultSet.getString("name_of_user");

                user.setPhoneNumber(phoneNumber);
                user.setName(name);

                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
