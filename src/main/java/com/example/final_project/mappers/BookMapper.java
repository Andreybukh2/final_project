package com.example.final_project.mappers;

import com.example.final_project.DTO.BookDto;
import com.example.final_project.models.Book;
import org.mapstruct.Mapper;

@Mapper
public interface BookMapper {
    BookDto toDto (Book book);
    Book toEntity (BookDto bookDto);
}
