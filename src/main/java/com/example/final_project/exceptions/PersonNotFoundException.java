package com.example.final_project.exceptions;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(){
        super("Пользователь с таким email не найден, проверьте правильность ввода " );
    }
}
