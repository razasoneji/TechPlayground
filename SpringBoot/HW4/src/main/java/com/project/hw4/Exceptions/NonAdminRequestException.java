package com.project.hw4.Exceptions;

public class NonAdminRequestException extends RuntimeException {
    public NonAdminRequestException(String s) {

   super(s); }
}
