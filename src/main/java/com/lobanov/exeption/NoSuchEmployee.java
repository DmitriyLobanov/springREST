package com.lobanov.exeption;

public class NoSuchEmployee extends RuntimeException{

    public NoSuchEmployee(String message) {
        super(message);
    }
}
