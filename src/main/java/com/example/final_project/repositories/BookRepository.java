package com.example.final_project.repositories;

import com.example.final_project.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByName(String name);
}
