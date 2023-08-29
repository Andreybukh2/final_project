package com.example.final_project.exceptions;

public class BookWithIdNotFoundException extends RuntimeException {
    public BookWithIdNotFoundException(){
        super("Книга с таким Id не найдена!");
    }
}
