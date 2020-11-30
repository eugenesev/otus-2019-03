package ru.otus.hw13.sessionmanager.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.hw13.api.exceptions.SessionManagerException;
import ru.otus.hw13.api.sessionmanager.DatabaseSession;
import ru.otus.hw13.api.sessionmanager.SessionManager;

@Component
public class SessionManagerHibernate implements SessionManager {

  private DatabaseSession databaseSession;
  private final SessionFactory sessionFactory;

  @Autowired
  public SessionManagerHibernate(SessionFactory sessionFactory) {
    if (sessionFactory == null) {
      throw new SessionManagerException("SessionFactory is null");
    }
    this.sessionFactory = sessionFactory;
  }

  @Override
  public void beginSession() {
    try {
      databaseSession = new DatabaseSessionHibernate(sessionFactory.openSession());
    } catch (Exception e) {
      throw new SessionManagerException(e);
    }
  }

  @Override
  public void commitSession() {
    checkSessionAndTransaction();
    try {
      databaseSession.getTransaction().commit();
      databaseSession.getSession().close();
    } catch (Exception e) {
      throw new SessionManagerException(e);
    }
  }

  @Override
  public void rollbackSession() {
    checkSessionAndTransaction();
    try {
      databaseSession.getTransaction().rollback();
      databaseSession.getSession().close();
    } catch (Exception e) {
      throw new SessionManagerException(e);
    }
  }

  @Override
  public void close() {
    if (databaseSession == null) {
      return;
    }
    Session session = databaseSession.getSession();
    if (session == null || !session.isConnected()) {
      return;
    }

    Transaction transaction = databaseSession.getTransaction();
    if (transaction == null || !transaction.isActive()) {
      return;
    }

    try {
      databaseSession.close();
      databaseSession = null;
    } catch (Exception e) {
      throw new SessionManagerException(e);
    }
  }

  @Override
  public DatabaseSession getCurrentSession() {
    checkSessionAndTransaction();
    return databaseSession;
  }

  private void checkSessionAndTransaction() {
    if (databaseSession == null) {
      throw new SessionManagerException("DatabaseSession not opened ");
    }
    Session session = databaseSession.getSession();
    if (session == null || !session.isConnected()) {
      throw new SessionManagerException("Session not opened ");
    }

    Transaction transaction = databaseSession.getTransaction();
    if (transaction == null || !transaction.isActive()) {
      throw new SessionManagerException("Transaction not opened ");
    }
  }
}
