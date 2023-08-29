package com.example.final_project.exceptions;

public class PersonWithIdNotFoundException extends RuntimeException {
    public PersonWithIdNotFoundException(){
        super("Пользователь с таким Id не найден!");
    }
}
