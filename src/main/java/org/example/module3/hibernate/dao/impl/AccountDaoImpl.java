package org.example.module3.hibernate.dao.impl;

import org.apache.log4j.Logger;
import org.example.module3.hibernate.dao.interfaces.AccountDao;
import org.example.module3.hibernate.entity.Account;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AccountDaoImpl implements AccountDao {

    private static final Logger logger = Logger.getLogger(AccountDaoImpl.class);

    private final Session session;

    public AccountDaoImpl(Session session) {
        this.session = session;
    }

    @Override
    public void update(Account account) {
        Transaction transaction = session.beginTransaction();
        try{
            logger.info("Account " + account + " has been updated");
            session.update(account);
            transaction.commit();
        }catch (Exception e) {
            logger.info("Account " + account + " has not been updated");
            transaction.rollback();
        }
    }

    @Override
    public Account findAccountById(Long id, List<Account> accounts) {
        Account currentAccount = null;
        for(Account account : accounts) {
            if(account.getId().equals(id)) {
                currentAccount = account;
            }
        }
        return currentAccount;
    }

    @Override
    public void delete(Account account) {
        Transaction transaction = session.beginTransaction();
        try{
            logger.info("Account " + account + " has been deleted");
            session.delete(account);
            transaction.commit();
        }catch (Exception e) {
            logger.info("Account " + account + " has not been deleted");
            transaction.rollback();
        }
    }
}
