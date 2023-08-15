package com.example.final_project.services;

import com.example.final_project.models.Person;

public interface JwtTokenService {
    String getToken(Person person);
}
