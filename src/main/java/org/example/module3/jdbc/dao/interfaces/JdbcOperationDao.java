package org.example.module3.jdbc.dao.interfaces;

import org.example.module3.jdbc.entity.Operation;

import java.sql.Connection;
import java.util.List;

public interface JdbcOperationDao {
    List<Operation> findOperationsBetweenTwoDates(Long accountId, String from, String to, Connection connection);
}
