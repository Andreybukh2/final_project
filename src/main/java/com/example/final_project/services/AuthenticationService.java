package com.example.final_project.services;

import com.example.final_project.DTO.AuthDto;


public interface AuthenticationService {

   String authenticate(AuthDto authDto); //В параметры передаем нашу модель DTO для аутентификации и возвращать будем токен;

}
