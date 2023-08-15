package com.example.final_project.servicesimpl;

import com.example.final_project.DTO.AuthDto;
import com.example.final_project.exceptions.PersonNotFoundException;
import com.example.final_project.models.Person;
import com.example.final_project.reposetories.PersonRepository;
import com.example.final_project.services.AuthenticationService;
import com.example.final_project.services.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtTokenService jwtTokenService;
    private final AuthenticationManager authenticationManager;
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;
    private final static String ERROR_MASSAGE = "Ошибка аутентификации";
    private final static String WRONG_PASSWORD = "Неверный пароль";
    @Override
    public String authenticate(AuthDto authDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(authDto.getEmail(), authDto.getPassword());
        Person person;
        try {
            person = personRepository.findByEmail(authDto.getEmail()).orElseThrow(PersonNotFoundException::new);
            if (person.getPassword().equals(passwordEncoder.encode(authDto.getPassword()))){
                authenticationManager.authenticate(authenticationToken);
            } else {
                return WRONG_PASSWORD;
            }
        } catch (RuntimeException e){
            return ERROR_MASSAGE;
        }
        return jwtTokenService.getToken(person);
    }
}
