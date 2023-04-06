package com.example.Lab5.exceptions;

public class HospitalNotFoundException extends RuntimeException{
    public HospitalNotFoundException(Long id){
        super("Could not find hospital "+ id);
    }
}
