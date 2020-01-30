package ru.otus.hw10.api.exceptions;

public class DbServiceException extends HomeWorkException {
  public DbServiceException(Exception e) {
    super(e);
  }
}
