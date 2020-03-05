package ru.otus.hw10.api.dao;

import ru.otus.hw10.api.model.User;
import ru.otus.hw10.api.sessionmanager.SessionManager;

import java.util.List;
import java.util.Optional;

public interface UserDao {
  Optional<User> findById(long id);

  long saveUser(User user);

  List<User> getAllUsers();

  SessionManager getSessionManager();
}
