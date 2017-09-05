package com.prem.studyproject.listeners;


import com.prem.studyproject.domain.model.MailConfirmationToken;
import com.prem.studyproject.domain.model.User;
import com.prem.studyproject.listeners.events.RegistrationUserEvent;
import com.prem.studyproject.services.RegistrationService;
import com.prem.studyproject.services.mail.MailSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class MailConfirmationListener {

    private MailSender confirmationAdressSender;
    private RegistrationService registrationService;

    @Autowired
    public MailConfirmationListener(@Qualifier("emailConfirmSender") MailSender confirmationAdressSender,
                                    RegistrationService registrationService) {
        this.confirmationAdressSender = confirmationAdressSender;
        this.registrationService = registrationService;
    }

    @EventListener
    public void sendActivationMail(RegistrationUserEvent event) {
        User user = event.getUser();
        MailConfirmationToken token = registrationService.getToken(user);
        Map<String, Object> values = new HashMap<>();
        values.put("user", user);
        values.put("token", token);
        try {
            confirmationAdressSender.send(values);
        } catch (MessagingException e) {
            log.error("Error while sending email message", e);
        }
    }
}
