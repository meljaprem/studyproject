package com.prem.studyproject.services.mail;


import com.prem.studyproject.domain.model.EmailConfirmationToken;
import com.prem.studyproject.domain.model.User;
import org.springframework.stereotype.Service;

@Service
public class EmailConfirmationAdressSender {

    public void send(User user, EmailConfirmationToken token) {
        System.out.println(user + "\n" + token);
    }
}
