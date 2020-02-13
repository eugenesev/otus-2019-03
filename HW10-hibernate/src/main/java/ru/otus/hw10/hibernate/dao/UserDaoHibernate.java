package ru.otus.hw10.hibernate.dao;


import org.hibernate.Criteria;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.ls.LSOutput;
import ru.otus.hw10.api.dao.UserDao;
import ru.otus.hw10.api.exceptions.UserDaoException;
import ru.otus.hw10.api.model.User;
import ru.otus.hw10.api.sessionmanager.SessionManager;
import ru.otus.hw10.hibernate.sessionmanager.DatabaseSessionHibernate;
import ru.otus.hw10.hibernate.sessionmanager.SessionManagerHibernate;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoHibernate implements UserDao {
    private static Logger logger = LoggerFactory.getLogger(UserDaoHibernate.class);

    private final SessionManagerHibernate sessionManager;

    public UserDaoHibernate(SessionManagerHibernate sessionManager) {
        this.sessionManager = sessionManager;
    }


    @Override
    public Optional<User> findById(long id) {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try {
            return Optional.ofNullable(currentSession.getHibernateSession().find(User.class, id));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return Optional.empty();
    }


    @Override
    public long saveUser(User user) {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try {
            Session hibernateSession = currentSession.getHibernateSession();
            if (user.getId() > 0) {
                hibernateSession.merge(user);
            } else {
                hibernateSession.persist(user);
            }
            return user.getId();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new UserDaoException(e);
        }
    }

    @Override
    public SessionManager getSessionManager() {
        return sessionManager;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
                DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try {
            Session hibernateSession = currentSession.getHibernateSession();
            CriteriaBuilder cb = hibernateSession.getCriteriaBuilder();
            hibernateSession.createQuery("from User").list();
            CriteriaQuery<User> userCriteria = cb.createQuery(User.class);
            Root<User> userRoot = userCriteria.from(User.class);
            userCriteria.select(userRoot);

            list.addAll(hibernateSession.createQuery(userCriteria).getResultList());
            list.forEach(user -> user.getPhone().isEmpty());


        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new UserDaoException(e);
        }
        return list;
    }




}
