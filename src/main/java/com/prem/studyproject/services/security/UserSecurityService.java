package com.prem.studyproject.services.security;

import com.prem.studyproject.domain.model.User;
import com.prem.studyproject.repository.interfaces.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class UserSecurityService implements UserDetailsService {

    private UserRepository repository;

    @Autowired
    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        String username = s.trim().toLowerCase();
        log.debug("Trying to find user " + username);
        UserDetails user = (UserDetails) repository.findFirstByUsername(s);
        if(user==null){
            throw new BadCredentialsException("Cant find user with username " + username);
        }
        log.debug("Loaded user: " + user);

        return user;
    }


}
