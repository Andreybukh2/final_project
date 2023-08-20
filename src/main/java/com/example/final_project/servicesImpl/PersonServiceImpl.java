package com.example.final_project.servicesImpl;

import com.example.final_project.DTO.PersonDto;
import com.example.final_project.mappers.PersonMapper;
import com.example.final_project.models.Person;
import com.example.final_project.models.Role;
import com.example.final_project.repositories.PersonRepository;
import com.example.final_project.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public PersonDto savePerson(PersonDto personDto) {
        personRepository.save(Person.builder()
                        .name(personDto.getName())
                        .email(personDto.getEmail())
                        .age(personDto.getAge())
                        .password(passwordEncoder.encode(personDto.getPassword()))
                        .role(Role.USER)
                        .phoneNumber(personDto.getPhoneNumber())
                        .createdAt(LocalDateTime.now())
                        .createdPerson(personDto.getName())
                .build());

        return personMapper.toDto(personRepository.findByEmail(personDto.getEmail()).get());
    }

    @Override
    public PersonDto getPerson(Long personId) {
        return null;
    }

    @Override
    public List<PersonDto> getAllPersons() {
        return null;
    }

    @Override
    public PersonDto updatePerson(PersonDto personDto, Long personId) {
        return null;
    }

    @Override
    public PersonDto deletePerson(Long personId) {
        return null;
    }
}
