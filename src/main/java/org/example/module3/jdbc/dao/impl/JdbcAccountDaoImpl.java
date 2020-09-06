package org.example.module3.jdbc.dao.impl;

import org.example.module3.jdbc.dao.interfaces.JdbcAccountDao;
import org.example.module3.jdbc.entity.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcAccountDaoImpl implements JdbcAccountDao {
    @Override
    public Account findAccountById(Long accountId, Connection connection) {

        Account account = new Account();

        try (PreparedStatement getAccount = connection.prepareStatement(
                "SELECT * FROM accounts WHERE id = ?")) {

            getAccount.setLong(1, accountId);

            ResultSet resultSet = getAccount.executeQuery();

            while (resultSet.next()) {

                String balance = resultSet.getString("balance");
                long userId = resultSet.getLong("user_id");

                account.setBalance(balance);
                account.setUserId(userId);

                return account;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
