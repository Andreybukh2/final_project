package com.example.final_project.services;

import com.example.final_project.DTO.BookDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BookService {
    BookDto saveBook(BookDto bookDto);
    BookDto getBook(Long bookId);
    List<BookDto> getAllBooks();
    BookDto updateBook(BookDto bookDto, Long bookId);
    String deleteBook(Long bookId);

}
