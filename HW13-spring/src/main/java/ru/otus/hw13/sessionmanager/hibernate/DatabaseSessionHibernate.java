package ru.otus.hw13.sessionmanager.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.otus.hw13.api.sessionmanager.DatabaseSession;

public class DatabaseSessionHibernate implements DatabaseSession {

  private final Session session;
  private final Transaction transaction;

  DatabaseSessionHibernate(Session session) {
    this.session = session;
    this.transaction = session.beginTransaction();
  }
  @Override
  public Session getSession() {
    return session;
  }
  @Override
  public Transaction getTransaction() {
    return transaction;
  }
  @Override
  public void close() {
    if (transaction.isActive()) {
      transaction.commit();
    }
    session.close();
  }
}
