package com.example.final_project.DTO;

import lombok.Data;

@Data
public class BookDto {
    private Long id;
    private String name;
    private Integer yearOfProduction;
    private String author;
    private String annotation;
}
