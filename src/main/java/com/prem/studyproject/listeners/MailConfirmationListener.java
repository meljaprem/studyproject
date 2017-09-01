package com.prem.studyproject.listeners;


import com.prem.studyproject.domain.model.EmailConfirmationToken;
import com.prem.studyproject.domain.model.User;
import com.prem.studyproject.listeners.events.RegistrationUserEvent;
import com.prem.studyproject.services.RegistrationService;
import com.prem.studyproject.services.mail.EmailConfirmationAdressSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MailConfirmationListener {

    private EmailConfirmationAdressSenderService confirmationAdressSenderService;
    private RegistrationService registrationService;

    @Autowired
    public MailConfirmationListener(EmailConfirmationAdressSenderService confirmationAdressSenderService, RegistrationService registrationService) {
        this.confirmationAdressSenderService = confirmationAdressSenderService;
        this.registrationService = registrationService;
    }

    @EventListener
    public void sendActivationMail(RegistrationUserEvent event){
        User user = event.getUser();
        EmailConfirmationToken token = registrationService.getToken(user);
        confirmationAdressSenderService.send(user, token);
    }
}
