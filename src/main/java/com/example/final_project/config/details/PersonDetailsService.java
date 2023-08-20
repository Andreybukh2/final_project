package com.example.final_project.config.details;

import com.example.final_project.exceptions.PersonNotFoundException;
import com.example.final_project.models.Person;
import com.example.final_project.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonDetailsService implements UserDetailsService {

    private final PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Person person = personRepository.findByEmail(email).orElseThrow(PersonNotFoundException::new);

        return new PersonDetails(person);
    }
}
