package com.example.Lab5.exceptions;

public class DoctorNotFoundException extends RuntimeException{
    public DoctorNotFoundException(Long id){
        super("Could not find doctor "+ id);
    }
}
