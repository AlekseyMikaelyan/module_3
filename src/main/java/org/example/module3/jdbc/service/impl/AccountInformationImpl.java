package org.example.module3.jdbc.service.impl;

import au.com.bytecode.opencsv.CSVWriter;
import org.example.module3.jdbc.dao.impl.JdbcAccountDaoImpl;
import org.example.module3.jdbc.dao.impl.JdbcOperationDaoImpl;
import org.example.module3.jdbc.entity.Operation;
import org.example.module3.jdbc.service.interfaces.AccountInformation;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class AccountInformationImpl implements AccountInformation {

    private final JdbcAccountDaoImpl accountDao;
    private final JdbcOperationDaoImpl operationDao;
    private final Connection connection;

    public AccountInformationImpl(JdbcAccountDaoImpl accountDao, JdbcOperationDaoImpl operationDao, Connection connection) {
        this.accountDao = accountDao;
        this.operationDao = operationDao;
        this.connection = connection;
    }

    @Override
    public void setInformationAboutAccountToCsvFile(Long accountId, String from, String to) throws Exception {

        List<Operation> operationList = operationDao.findOperationsBetweenTwoDates(accountId, from, to, connection);

        long saldo = getSaldo(accountId, from, to);
        String csv = "accountInformation.csv";
        try (CSVWriter writer = new CSVWriter(new FileWriter(csv))) {

            String[] header = "Account_Id,Amount,Timestamp,Saldo".split(",");

            writer.writeNext(header);

            for (int i = 0; i < operationList.size(); i++) {
                String[] data  = (accountId.toString() + "," + operationList.get(i).getAmount() + "," + "," + operationList.get(i).getTimestamp() + "," + "," + saldo).split(",");
                writer.writeNext(data);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Long getSaldo(Long accountId, String from, String to) {
        List<Operation> operationList = operationDao.findOperationsBetweenTwoDates(accountId, from, to, connection);

        long saldo = 0;

        for(int i = 0; i < operationList.size(); i++) {
            saldo += operationList.get(i).getAmount();
        }
        return saldo;
    }

}
