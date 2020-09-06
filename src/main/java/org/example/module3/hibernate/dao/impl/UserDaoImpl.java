package org.example.module3.hibernate.dao.impl;

import org.apache.log4j.Logger;
import org.example.module3.hibernate.dao.interfaces.UserDao;
import org.example.module3.hibernate.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDaoImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

    private final Session session;

    public UserDaoImpl(Session session) {
        this.session = session;
    }

    @Override
    public void update(User user) {
        Transaction transaction = session.beginTransaction();
        try{
            logger.info("User " + user + " has been updated");
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            logger.info("User " + user + " has not been update");
            transaction.rollback();
        }
    }

    @Override
    public User findUserById(Long id) {
            logger.info("User with " + id + " id was found");
            return session.get(User.class, id);
    }

    @Override
    public void delete(User user) {
        Transaction transaction = session.beginTransaction();
        try{
            logger.info("User " + user + " has been deleted");
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            logger.info("User " + user + " has not been deleted");
            transaction.rollback();
        }
    }
}
