package com.prem.studyproject.services.mail;


import com.prem.studyproject.domain.enums.TemplateType;
import com.prem.studyproject.domain.model.MailConfirmationToken;
import com.prem.studyproject.domain.model.User;
import com.prem.studyproject.helpers.MailBuilder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
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

    private String subject;

    public EmailConfirmationAdressSender(JavaMailSender sender) {
        super(sender);
    }

    @Override
    @Async
    public void send(Map<String, Object> values) throws MessagingException {
        log.debug("send() method invoke. Values : {}", values);
        if (!values.isEmpty() && values.containsKey("user") && values.containsKey("token")) {
            String temaplate = getTemplate(TemplateType.REG_TOKEN);
            User user = (User) values.get("user");
            MailConfirmationToken token = (MailConfirmationToken) values.get("token");
            String body = replaceAll(temaplate, token, user);
            MailBuilder builder = new MailBuilder(sender);
            builder.setTO(user.getEmail())
                    .setSubject(subject)
                    .setText(body);
            MimeMessage message = builder.generateMimeMessage();
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
