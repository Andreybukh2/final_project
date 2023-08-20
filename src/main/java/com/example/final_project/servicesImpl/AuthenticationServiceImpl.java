package com.example.final_project.servicesImpl;

import com.example.final_project.DTO.AuthDto;
import com.example.final_project.exceptions.PersonNotFoundException;
import com.example.final_project.models.Person;
import com.example.final_project.repositories.PersonRepository;
import com.example.final_project.services.AuthenticationService;
import com.example.final_project.services.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtTokenService jwtTokenService;
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;
    private final static String WRONG_PASSWORD = "Неверный пароль";
    @Override
    public String authenticate(AuthDto authDto) {

        Person person = personRepository.findByEmail(authDto.getEmail()).orElseThrow(PersonNotFoundException::new);
            if (!passwordEncoder.matches (authDto.getPassword(), person.getPassword())){
              throw new IllegalStateException(WRONG_PASSWORD);
            }
        return jwtTokenService.getToken(person);
    }
}
