package org.example.module3.main;

import org.example.module3.hibernate.dao.impl.OperationDaoImpl;
import org.example.module3.hibernate.dao.interfaces.OperationDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateMain {
    public static void main(String[] args) {
        Long userId = Long.parseLong(args[0]);
        String username = args[1];
        String password = args[2];
        long amount = Long.parseLong(args[3]);

        Configuration configuration = new Configuration();
        configuration.configure();

        configuration.setProperty("hibernate.connection.username", username);
        configuration.setProperty("hibernate.connection.password", password);


        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            OperationDao operationDao = new OperationDaoImpl(session);

            operationDao.workWithUserAccount(userId, amount);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
