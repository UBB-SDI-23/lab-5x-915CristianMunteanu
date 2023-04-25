package com.example.Lab5.exceptions;

public class PatientNotFoundException extends RuntimeException{
    public PatientNotFoundException(Long id){
        super("Could not find patient "+ id);
    }
}
