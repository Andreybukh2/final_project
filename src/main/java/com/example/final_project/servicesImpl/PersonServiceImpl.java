package com.example.final_project.servicesImpl;

import com.example.final_project.DTO.BookDto;
import com.example.final_project.DTO.PersonDto;
import com.example.final_project.exceptions.PersonWithIdNotFoundException;
import com.example.final_project.mappers.BookMapper;
import com.example.final_project.mappers.PersonMapper;
import com.example.final_project.models.Book;
import com.example.final_project.models.Person;
import com.example.final_project.models.Role;
import com.example.final_project.repositories.BookRepository;
import com.example.final_project.repositories.PersonRepository;
import com.example.final_project.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    private final PasswordEncoder passwordEncoder;
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

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
        return personMapper.toDto(personRepository.findById(personId).orElseThrow(PersonWithIdNotFoundException::new));
    }

    @Override
    public List<PersonDto> getAllPersons() {
        return personRepository.findAll().stream().map(personMapper::toDto).collect(Collectors.toList());
    }
    @Override
    public PersonDto updatePerson(PersonDto personDto, Long personId) {
        Person personForUpdate = personRepository.findById(personId).orElseThrow(PersonWithIdNotFoundException::new);

        if(personDto.getName() !=null){
            personForUpdate.setName(personDto.getName());
        }
        if (personDto.getAge() !=null) {
            personForUpdate.setAge(personDto.getAge());
        }
        if (personDto.getPhoneNumber() !=null){
            personForUpdate.setPhoneNumber(personDto.getPhoneNumber());
        }
        if (personDto.getPassword() !=null) {
            personForUpdate.setPassword(passwordEncoder.encode(personDto.getPassword()));
        }
        if (personDto.getEmail() !=null){
            personForUpdate.setEmail(personDto.getEmail());
        }
        personRepository.save(personForUpdate);

        return personMapper.toDto(personForUpdate);
    }

    @Override
    public String deletePerson(Long personId) {
        personRepository.deleteById(personId);
        return "Пользователь с Id " + personId + " был удален";
    }

    @Override
    public List<BookDto> takeBook(Long personId, Long bookId) {
        var person = personRepository.findById(personId).orElseThrow(PersonWithIdNotFoundException::new);
        List<Book> books = person.getBooks();
        if(books == null){
            books = new ArrayList<>();
        }
        books.add(bookRepository.findById(bookId).get());
        person.setBooks(books);
        personRepository.save(person);
        return person.getBooks().stream().map(bookMapper::toDto).collect(Collectors.toList());
    }
}
