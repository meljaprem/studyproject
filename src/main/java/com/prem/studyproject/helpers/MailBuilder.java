package com.prem.studyproject.helpers;

import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.List;

/**
 * @author Melnyk_Dmytro
 * @version 1.0
 * @since 05.09.2017
 */

public class MailBuilder {
    private MimeMultipart multipart;
    private JavaMailSender sender;
    private MimeMessage message;

    public MailBuilder(JavaMailSender sender) {
        this.sender = sender;
        message = sender.createMimeMessage();
    }

    public MimeMessage generateMimeMessage() throws MessagingException {
        message.setContent(multipart);
        return message;
    }

    public MailBuilder setTO(List<String> to) throws MessagingException {
        InternetAddress[] addresses = new InternetAddress[to.size()];
        for (int i = 0; i < to.size(); i++) {
            addresses[i] = new InternetAddress(to.get(i));
        }
        message.setRecipients(Message.RecipientType.TO, addresses);
        return this;
    }

    public MailBuilder setTO(String to) throws MessagingException {
        message.setRecipients(Message.RecipientType.TO, to);
        return this;
    }

    public MailBuilder setText(Object obj) throws MessagingException {
        setContent(obj, "text/html; charset=utf-8");
        return this;
    }


    public MailBuilder setSubject(String subject) throws MessagingException {
        message.setSubject(subject);
        return this;
    }


    private void setContent(Object obj, String type) throws MessagingException {
        multipart = new MimeMultipart();
        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(obj, type);
        multipart.addBodyPart(bodyPart);
    }
}
