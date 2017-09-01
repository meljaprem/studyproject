package com.prem.studyproject.services.security;

import com.prem.studyproject.domain.enums.Role;
import com.prem.studyproject.domain.model.User;
import com.prem.studyproject.repository.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class UserSecurityService implements UserDetailsService {

    private UserRepository repository;

    @Autowired
    public UserSecurityService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        String username = s.trim().toLowerCase();
        return (UserDetails) repository.findFirstByUsername(s);
    }
}
