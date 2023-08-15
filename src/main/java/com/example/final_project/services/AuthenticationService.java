package com.example.final_project.services;

import com.example.final_project.DTO.AuthDto;


public interface AuthenticationService {

   String authenticate(AuthDto authDto); // Возвращает токен


}
