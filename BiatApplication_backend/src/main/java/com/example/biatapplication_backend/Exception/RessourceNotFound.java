package com.example.biatapplication_backend.Exception;

public class RessourceNotFound extends RuntimeException{
    public RessourceNotFound(String message){
        super(message);
    }
}
