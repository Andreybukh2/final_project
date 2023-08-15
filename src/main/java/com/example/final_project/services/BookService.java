package com.example.final_project.services;

import com.example.final_project.DTO.BookDto;

import java.util.List;

public interface BookService {
    BookDto saveBook(BookDto bookDto);
    BookDto getBook(Long bookId);
    List<BookDto> getAllBooks();
    BookDto updateBook(BookDto bookDto, Long bookId);
    BookDto deletePerson(Long bookId);
}
