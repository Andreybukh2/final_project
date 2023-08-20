package com.example.final_project.controllers;

import com.example.final_project.DTO.BookDto;
import com.example.final_project.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/saveBook")
    @PreAuthorize("hasAnyAuthority(@getAuthorities.ROLE_ADMIN, @getAuthorities.ROLE_USER)")

    public ResponseEntity<BookDto> saveBook(@RequestBody BookDto bookDto) {
        return ResponseEntity.ok(bookService.saveBook(bookDto));
    }
}
