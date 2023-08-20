package com.example.final_project.controllers;

import com.example.final_project.DTO.AuthDto;
import com.example.final_project.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @GetMapping("/signIn")
    public ResponseEntity<String> authenticate(@RequestBody AuthDto authDto){
        return ResponseEntity.ok().body(authenticationService.authenticate(authDto));
    }
}
