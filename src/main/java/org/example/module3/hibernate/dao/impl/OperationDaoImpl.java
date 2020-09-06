package org.example.module3.hibernate.dao.impl;

import org.apache.log4j.Logger;
import org.example.module3.hibernate.dao.interfaces.AccountDao;
import org.example.module3.hibernate.dao.interfaces.OperationDao;
import org.example.module3.hibernate.dao.interfaces.UserDao;
import org.example.module3.hibernate.entity.Account;
import org.example.module3.hibernate.entity.Operation;
import org.example.module3.hibernate.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.Clock;
import java.time.Instant;
import java.util.List;

public class OperationDaoImpl implements OperationDao {

    private static final Logger logger = Logger.getLogger(OperationDaoImpl.class);

    private final Session session;

    public OperationDaoImpl(Session session) {
        this.session = session;
    }

    @Override
    public void workWithUserAccount(Long id, long amount) {

        AccountDao accountDao = new AccountDaoImpl(session);
        UserDao userDao = new UserDaoImpl(session);

        User user = userDao.findUserById(id);
        List<Account> accounts = user.getAccountList();
        Account account = accountDao.findAccountById(id, accounts);

        addNewOperation(account, amount);
    }

    @Override
    public Operation addNewOperation(Account account, long amount) {
        Operation operation = null;
        if(amount > 0) {
            logger.info("A new income operation was added from account " + account.getId());
            operation = incomeOperation(account, amount);
        }else if(amount < 0 && (account.getBalance() - amount) > 0) {
            logger.info("A new expense operation was added from account " + account.getId());
            operation = expenseOperation(account, amount);
        }
        return operation;
    }

    @Override
    public Operation incomeOperation(Account account, long amount) {
        Operation operation = null;
        Transaction transaction = session.beginTransaction();
        try{
            operation = new Operation();
            operation.setAccount(account);
            operation.setAmount(amount);
            operation.setTimestamp(Instant.now(Clock.systemUTC()));

            account.setBalance(account.getBalance() + operation.getAmount());
            session.save(operation);
            transaction.commit();
        }catch (Exception e) {
            logger.info("Income operation was not save " + e);
        }
        return operation;
    }

    @Override
    public Operation expenseOperation(Account account, long amount) {
        Operation operation = null;
        Transaction transaction = session.beginTransaction();
        try{
            operation = new Operation();
            operation.setAccount(account);
            operation.setAmount(amount);
            operation.setTimestamp(Instant.now(Clock.systemUTC()));

            account.setBalance(account.getBalance() + operation.getAmount());
            session.saveOrUpdate(operation);
            transaction.commit();
        }catch (Exception e) {
            logger.info("Expense operation was not save " + e);
        }
        return operation;
    }
}
