package com.prem.studyproject.services.mail;


import com.prem.studyproject.domain.enums.TemplateType;
import com.prem.studyproject.domain.model.MailConfirmationToken;
import com.prem.studyproject.domain.model.User;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Conditional;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
@Qualifier("emailConfirmSender")
@ConfigurationProperties("mail.reg.token")
@Data
@Slf4j
public class EmailConfirmationAdressSender extends MailSender {

    private static final String USERNAME_TMPL = "%username%";
    private static final String TOKEN_TMPL = "%token%";
    private static final String NAME_TMPL = "%name%";
    private static final String SURNAME_TMPL = "%surname%";

    private org.springframework.mail.MailSender sender;
    private String subject;

//    @Autowired
//    public EmailConfirmationAdressSender(org.springframework.mail.MailSender sender) {
//        this.sender = sender;
//    }

    @Override
    public void send(Map<String, Object> values) {
        log.debug("send() method invoke. Values : {}", values);
        if (!values.isEmpty() && values.containsKey("user") && values.containsKey("token")) {
            String temaplate = getTemplate(TemplateType.REG_TOKEN);
            User user = (User) values.get("user");
            MailConfirmationToken token = (MailConfirmationToken) values.get("token");
            String body = replaceAll(temaplate, token, user);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setText(body);
            message.setSubject(subject);
            log.debug("Sending message");
            sender.send(message);
        } else {
            throw new IllegalArgumentException("Map of values must contain 'user' and 'token' keys");
        }
    }


    private String replaceAll(String oldTemplate, MailConfirmationToken token, User user) {
        log.debug("replaceAll method invoke. oldTemplate: {}, token : {}, user : {} ", oldTemplate, token, user);
        return oldTemplate
                .replaceAll(USERNAME_TMPL, user.getUsername())
                .replaceAll(TOKEN_TMPL, token.getValue())
                .replaceAll(NAME_TMPL, user.getName())
                .replaceAll(SURNAME_TMPL, user.getSurname());
    }
}
