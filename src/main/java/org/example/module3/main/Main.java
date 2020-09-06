package org.example.module3.main;

import org.example.module3.hibernate.entity.Account;
import org.example.module3.hibernate.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        User user1 = new User();
        user1.setName("Alex");
        user1.setPhoneNumber("0688933900");

        User user2 = new User();
        user2.setName("Ivan");
        user2.setPhoneNumber("0636435199");

        Account account1 = new Account();
        account1.setUser(user1);
        account1.setBalance(1500L);

        Account account2 = new Account();
        account2.setUser(user2);
        account2.setBalance(1000L);

        Configuration configuration = new Configuration().configure();

        configuration.setProperty("hibernate.connection.username", "postgres");
        configuration.setProperty("hibernate.connection.password", "postgres");

        try(SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.save(user1);
                session.save(account1);
                session.save(user2);
                session.save(account2);
                transaction.commit();

            }catch (Exception e) {
                transaction.rollback();
            }
        }
    }
}
