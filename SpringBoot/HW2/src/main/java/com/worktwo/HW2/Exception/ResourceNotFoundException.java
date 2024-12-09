package com.worktwo.HW2.Exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String s) {
        super(s);
    }
}
