package org.example.module3.main;

import org.example.module3.jdbc.dao.impl.JdbcAccountDaoImpl;
import org.example.module3.jdbc.dao.impl.JdbcOperationDaoImpl;
import org.example.module3.jdbc.entity.Account;
import org.example.module3.jdbc.service.impl.AccountInformationImpl;
import org.example.module3.jdbc.service.interfaces.AccountInformation;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcMain {
    public static void main(String[] args) {

        long userId = Long.parseLong(args[0]);
        String username = args[1];
        String password = args[2];
        String url = "jdbc:postgresql://localhost:5432/module3";
        String from = "2020-09-05 15:00:00";
        String to = "2020-09-06 15:00:00";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {

            JdbcAccountDaoImpl accountDao = new JdbcAccountDaoImpl();
            JdbcOperationDaoImpl operationDao = new JdbcOperationDaoImpl();

            Long accountId = 1L;

            Account account = accountDao.findAccountById(accountId, connection);
            if(account.getUserId() != userId){
                throw new Exception("Account does not exist");
            }

            AccountInformation accountInformation = new AccountInformationImpl(accountDao, operationDao, connection);
            accountInformation.setInformationAboutAccountToCsvFile(accountId, from, to);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
