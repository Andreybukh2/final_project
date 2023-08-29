package com.example.final_project.controllers;

import com.example.final_project.DTO.BookDto;
import com.example.final_project.DTO.PersonDto;
import com.example.final_project.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/saveBook")
    @PreAuthorize("hasAnyAuthority(@getAuthorities.ROLE_ADMIN)")
    public ResponseEntity<BookDto> saveBook(@RequestBody BookDto bookDto) {
        return ResponseEntity.ok(bookService.saveBook(bookDto));
    }
    @PostMapping("/updateBook/{bookId}")
    @PreAuthorize("hasAnyAuthority(@getAuthorities.ROLE_ADMIN, @getAuthorities.ROLE_USER)")
    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto, @PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.updateBook(bookDto, bookId));
    }
    @GetMapping("/getBookId/{id}")
    @PreAuthorize("hasAnyAuthority(@getAuthorities.ROLE_ADMIN, @getAuthorities.ROLE_USER)")
    public ResponseEntity<BookDto> getBookId(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBook(id));
    }
    @GetMapping("/getAllBooks")
    @PreAuthorize("hasAnyAuthority(@getAuthorities.ROLE_ADMIN, @getAuthorities.ROLE_USER)")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }
    @DeleteMapping("/deleteBookId/{bookId}")
    @PreAuthorize("hasAnyAuthority(@getAuthorities.ROLE_ADMIN)")
    public ResponseEntity<String> deleteBook(@PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.deleteBook(bookId));
    }

}
