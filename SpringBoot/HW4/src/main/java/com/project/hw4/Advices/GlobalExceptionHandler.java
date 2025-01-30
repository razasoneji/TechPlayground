package com.project.hw4.Advices;


import com.project.hw4.Exceptions.ClientSideException;
import com.project.hw4.Exceptions.NonAdminRequestException;
import com.project.hw4.Exceptions.ServerSideException;
import com.project.hw4.Exceptions.ServiceProblemException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ClientSideException.class)
    public ResponseEntity<String> handleClientSideException(ClientSideException e) {
        return ResponseEntity.internalServerError().body(e.getMessage());
    }

    @ExceptionHandler(NonAdminRequestException.class)
    public ResponseEntity<String> nonAdmin(NonAdminRequestException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ServerSideException.class)
    public ResponseEntity<String> serverside(ServerSideException e) {
        return ResponseEntity.internalServerError().body(e.getMessage());
    }

    @ExceptionHandler(ServiceProblemException.class)
    public ResponseEntity<String> serviceProblem(ServiceProblemException e) {
        return ResponseEntity.internalServerError().body(e.getMessage());
    }


}
