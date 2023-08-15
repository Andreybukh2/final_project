package com.example.final_project.mappers;

import com.example.final_project.DTO.PersonDto;
import com.example.final_project.models.Person;
import org.mapstruct.Mapper;

@Mapper
public interface PersonMapper {
    PersonDto toDto (Person person);
    Person toEntity (PersonDto personDto);
}
