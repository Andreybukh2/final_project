package com.example.final_project.services;

import com.example.final_project.DTO.PersonDto;

import java.util.List;

public interface PersonService {

    PersonDto savePerson(PersonDto personDto);
    PersonDto getPerson(Long personId);
    List<PersonDto> getAllPersons();
    PersonDto updatePerson(PersonDto personDto, Long personId);
    PersonDto deletePerson(Long personId);
}
