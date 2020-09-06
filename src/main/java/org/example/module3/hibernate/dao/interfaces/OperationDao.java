package org.example.module3.hibernate.dao.interfaces;

import org.example.module3.hibernate.entity.Account;
import org.example.module3.hibernate.entity.Operation;

public interface OperationDao {
    void workWithUserAccount(Long id, long amount);
    Operation addNewOperation(Account account, long amount);
    Operation incomeOperation(Account account, long amount);
    Operation expenseOperation(Account account, long amount);
}
