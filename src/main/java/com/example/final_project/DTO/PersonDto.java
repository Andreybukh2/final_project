package com.example.final_project.DTO;

import com.example.final_project.models.Role;
import lombok.Data;

@Data
public class PersonDto {
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private String phoneNumber;
    private String password;
    private Role role;
}
