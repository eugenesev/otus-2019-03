package ru.otus.hw13.api.exceptions;


public class SessionManagerException extends HomeWorkException {
  public SessionManagerException(String msg) {
    super(msg);
  }

  public SessionManagerException(Exception ex) {
    super(ex);
  }
}
