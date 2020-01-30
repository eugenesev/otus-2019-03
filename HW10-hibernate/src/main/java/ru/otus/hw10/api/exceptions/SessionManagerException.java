package ru.otus.hw10.api.exceptions;


public class SessionManagerException extends HomeWorkException {
  public SessionManagerException(String msg) {
    super(msg);
  }

  public SessionManagerException(Exception ex) {
    super(ex);
  }
}
