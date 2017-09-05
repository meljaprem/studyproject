package com.prem.studyproject.domain.dto;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserDto {
    private String name;
    private String surname;
    private String password;
    private String username;
    private String email;
    private String city;
}
