package ru.otus.hw13.api.exceptions;

public class HomeWorkException extends RuntimeException {

    public HomeWorkException(Exception ex){super("Application Exception", ex);}

    public HomeWorkException(String msg){super(msg);}
}
