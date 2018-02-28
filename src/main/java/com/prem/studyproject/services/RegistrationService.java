package com.prem.studyproject.services;

import com.prem.studyproject.domain.enums.Role;
import com.prem.studyproject.domain.model.MailConfirmationToken;
import com.prem.studyproject.domain.model.User;
import com.prem.studyproject.listeners.events.RegistrationUserEvent;
import com.prem.studyproject.repository.interfaces.TokenRepository;
import com.prem.studyproject.repository.interfaces.UserRepository;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RegistrationService {

    private UserRepository userRepository;
    private TokenRepository tokenRepository;
    private ApplicationEventPublisher publisher;

    @Autowired
    public RegistrationService(UserRepository userRepository, TokenRepository tokenRepository, ApplicationEventPublisher publisher) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.publisher = publisher;
    }

    public User registerNewUser(User user) {
        List<Role> roles = new ArrayList<>();
        roles.add(Role.USER);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPass = encoder.encode(user.getPassword());
        String username = user.getUsername().trim().toLowerCase();

        user.setUsername(username);
        user.setRegDate(LocalDateTime.now());
        user.setAccountNonLocked(true);
        user.setAuthorities(roles);
        user.setAccountNonExpired(true);
        user.setEnabled(false);
        user.setCredentialsNonExpired(true);
        user.setPassword(encodedPass);

        userRepository.save(user);
        publisher.publishEvent(new RegistrationUserEvent(user, this));
        return user;
    }

    public User registerNewAdmin(User user) {
        List<Role> roles = new ArrayList<>();
        roles.add(Role.ADMIN);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPass = encoder.encode(user.getPassword());
        String username = user.getUsername().trim().toLowerCase();

        user.setUsername(username);
        user.setRegDate(LocalDateTime.now());
        user.setAccountNonLocked(true);
        user.setAuthorities(roles);
        user.setAccountNonExpired(true);
        user.setEnabled(true);
        user.setCredentialsNonExpired(true);
        user.setPassword(encodedPass);

        return userRepository.save(user);
    }

    public MailConfirmationToken getToken(User user) {
        MailConfirmationToken token = tokenRepository.findByUserId(user.getId());
        if (token == null) {
            token = generateToken(user);
        } else {
            token.setValue(UUID.randomUUID().toString());
            token.setCreationTime(LocalDateTime.now());
            token.setActive(true);
        }
        return tokenRepository.save(token);
    }

    public User confirmToken(String tokenValue) {
        User user = null;
        MailConfirmationToken token = tokenRepository.findByValue(tokenValue);
        if (token != null && token.isActive()) {
            token.setActive(false);
            tokenRepository.save(token);
            user = userRepository.findFirstById(token.getUserId());
            user.setEnabled(true);
            userRepository.save(user);
        }
        return user;
    }

    private MailConfirmationToken generateToken(User user) {
        MailConfirmationToken token = new MailConfirmationToken();
        String value = UUID.randomUUID().toString();
        token.setActive(true);
        token.setUserId(user.getId());
        token.setValue(value);
        token.setCreationTime(LocalDateTime.now());
        return token;
    }


}
