package com.example.final_project.servicesImpl;

import com.example.final_project.DTO.BookDto;
import com.example.final_project.exceptions.BookWithIdNotFoundException;
import com.example.final_project.mappers.BookMapper;
import com.example.final_project.models.Book;
import com.example.final_project.repositories.BookRepository;
import com.example.final_project.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    @Override
    public BookDto saveBook(BookDto bookDto) {
        bookRepository.save(Book.builder()
                .name(bookDto.getName())
                .yearOfProduction(bookDto.getYearOfProduction())
                .annotation(bookDto.getAnnotation())
                .author(bookDto.getAuthor())
                .createdAt(LocalDateTime.now())
                .build());
        return bookMapper.toDto(bookRepository.findByName(bookDto.getName()));
    }

    @Override
    public BookDto getBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(BookWithIdNotFoundException::new);
        return bookMapper.toDto(book);
    }

    @Override
    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream().map(bookMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public BookDto updateBook(BookDto bookDto, Long bookId) {
        Book bookForUpdate = bookRepository.findById(bookId).orElseThrow(BookWithIdNotFoundException::new);

        if(bookDto.getAuthor() !=null){
            bookForUpdate.setAuthor(bookDto.getAuthor());
        }
        if (bookDto.getAnnotation() !=null) {
            bookForUpdate.setAnnotation(bookDto.getAnnotation());
        }
        if (bookDto.getYearOfProduction() !=null){
            bookForUpdate.setYearOfProduction(bookDto.getYearOfProduction());
        }
        if (bookDto.getName() !=null) {
            bookForUpdate.setName(bookDto.getName());
        }
        bookRepository.save(bookForUpdate);
        return bookMapper.toDto(bookForUpdate);
    }

    @Override
    public String deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
        return "Книга с Id № " + bookId + " была удалена";
    }
}
