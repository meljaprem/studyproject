package com.prem.studyproject.domain.model;

import com.prem.studyproject.domain.enums.Role;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Collection;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "users")
@ToString
public class User implements UserDetails {
    @Id
    private BigInteger id;
    private String name;
    private String surname;
    private Collection<Role> authorities;
    private String password;
    @Indexed(unique = true)
    private String username;
//    @Indexed(unique = true)
    private String email;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private LocalDateTime regDate;
    private String city;
}
