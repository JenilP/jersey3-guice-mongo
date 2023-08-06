package com.monke.exception;

public class MonkeMongoException extends Exception {
    public MonkeMongoException(String message) {
        super(message);
    }
    public MonkeMongoException(Throwable e) {
        super(e);
    }
    public MonkeMongoException(String message, Throwable e) {
        super(message, e);
    }
}
