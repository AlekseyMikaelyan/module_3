package org.example.module3.jdbc.dao.impl;

import org.example.module3.jdbc.dao.interfaces.JdbcOperationDao;
import org.example.module3.jdbc.entity.Income;
import org.example.module3.jdbc.entity.Operation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcOperationDaoImpl implements JdbcOperationDao {
    @Override
    public List<Operation> findOperationsBetweenTwoDates(Long accountId, String from, String to, Connection connection) {

        List<Operation> operations = new ArrayList<>();
        Operation operation;

        try (PreparedStatement getListOperations = connection.prepareStatement(
                "SELECT * FROM operations WHERE account_id = ? AND timestamp > ? AND timestamp < ?")) {

            getListOperations.setLong(1, accountId);
            getListOperations.setTimestamp(2, Timestamp.valueOf(from));
            getListOperations.setTimestamp(3, Timestamp.valueOf(to));


            ResultSet resultSet = getListOperations.executeQuery();

            while (resultSet.next()) {
                operation = new Income();

                Long amount = resultSet.getLong("amount");
                Timestamp timestamp = resultSet.getTimestamp("timestamp");

                operation.setAmount(amount);
                operation.setAccountId(accountId);
                operation.setTimestamp(timestamp.toInstant());

                operations.add(operation);
            }

            return operations;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
