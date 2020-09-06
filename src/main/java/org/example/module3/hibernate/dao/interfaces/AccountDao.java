package org.example.module3.hibernate.dao.interfaces;

import org.example.module3.hibernate.entity.Account;

import java.util.List;

public interface AccountDao {
    void update(Account account);
    Account findAccountById(Long id, List<Account> accounts);
    void delete(Account account);
}
