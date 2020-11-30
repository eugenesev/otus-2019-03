package ru.otus.hw13.repository.hibernate;


import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.otus.hw13.api.dao.UserDao;
import ru.otus.hw13.api.exceptions.UserDaoException;
import ru.otus.hw13.api.sessionmanager.DatabaseSession;
import ru.otus.hw13.api.sessionmanager.SessionManager;
import ru.otus.hw13.domain.User;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoHibernate implements UserDao {
    private static Logger logger = LoggerFactory.getLogger(UserDaoHibernate.class);

    private final SessionManager sessionManager;

    @Autowired
    public UserDaoHibernate(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public Optional<User> findById(long id) {
        DatabaseSession currentSession = sessionManager.getCurrentSession();
        try {
            return Optional.ofNullable(currentSession.getSession().find(User.class, id));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return Optional.empty();
    }


    @Override
    public long saveUser(User user) {
        DatabaseSession currentSession = sessionManager.getCurrentSession();
        try {
            Session hibernateSession = currentSession.getSession();
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

        DatabaseSession currentSession = sessionManager.getCurrentSession();
        Session hibernateSession = currentSession.getSession();

        return hibernateSession.createQuery("from User").list();
    }


}
