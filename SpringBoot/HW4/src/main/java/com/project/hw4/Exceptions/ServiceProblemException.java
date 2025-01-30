package com.project.hw4.Exceptions;

public class ServiceProblemException extends RuntimeException {
    public ServiceProblemException(String someErrorInGetStatusOfApiOccured) {
        super(someErrorInGetStatusOfApiOccured);
    }
}
