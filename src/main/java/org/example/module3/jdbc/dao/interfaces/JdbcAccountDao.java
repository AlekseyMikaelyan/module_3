package org.example.module3.jdbc.dao.interfaces;

import org.example.module3.jdbc.entity.Account;

import java.sql.Connection;

public interface JdbcAccountDao {
    Account findAccountById(Long accountId, Connection connection);
}
