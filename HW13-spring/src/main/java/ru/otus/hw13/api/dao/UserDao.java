package ru.otus.hw13.api.dao;

import ru.otus.hw13.api.sessionmanager.SessionManager;
import ru.otus.hw13.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
  Optional<User> findById(long id);

  long saveUser(User user);

  List<User> getAllUsers();

  SessionManager getSessionManager();
}
