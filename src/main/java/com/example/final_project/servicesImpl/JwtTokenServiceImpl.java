package com.example.final_project.servicesImpl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.final_project.models.Person;
import com.example.final_project.services.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class JwtTokenServiceImpl implements JwtTokenService {


    @Override
    public String getToken(Person person) {

        Instant date = Instant.from(ZonedDateTime.now().plusHours(1));

        return JWT.create()
                .withClaim("email",person.getEmail())
                .withClaim("name", person.getName())
                .withClaim("age", person.getAge())
                .withClaim("phone", person.getPhoneNumber())
                .withClaim("role", person.getRole().name())
                .withExpiresAt(date)
                .sign(Algorithm.HMAC256("SecretKey"));
    }
}
