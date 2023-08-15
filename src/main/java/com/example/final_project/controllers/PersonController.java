package com.example.final_project.controllers;

import com.example.final_project.DTO.PersonDto;
import com.example.final_project.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;
    @PostMapping("/savePerson")
    @PreAuthorize("hasAnyAuthority(@getAuthorities.ROLE_ADMIN, @getAuthorities.ROLE_USER)")

    public ResponseEntity<PersonDto> savePerson(@RequestBody PersonDto personDto) {
        return ResponseEntity.ok(personService.savePerson(personDto));
    }

}
