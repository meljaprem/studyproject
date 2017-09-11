package com.prem.studyproject.services.mail;

import com.prem.studyproject.domain.enums.TemplateType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@Service
@PropertySource("classpath:application.properties")
@Slf4j
public abstract class MailSender {

    protected JavaMailSender sender;
    private static final String FOLDER = "mailTemplates";
    @Value("${spring.profiles.active}")
    private String profile;

    @Autowired
    public MailSender(JavaMailSender sender) {
        this.sender = sender;
    }

    public String getTemplate(TemplateType type) {
        log.debug("getTemplate method invoke : TemplateType : {}, FOLDER : {}, profile : {} ", type, FOLDER, profile);
        StringBuilder stringBuilder = new StringBuilder();
        if (profile.equals("prod")) {
            try {
                Resource resource = new ClassPathResource(FOLDER + File.separator + type.toString().toLowerCase() + ".html");
                BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()), 1024);
                String line;
                while ((line = br.readLine()) != null) {
                    stringBuilder.append(line);
                }
                br.close();
            } catch (IOException e) {
                log.error("Error while reading file", e);
            }
            return stringBuilder.toString();
        } else {
            String path = getClass().getClassLoader().getResource(FOLDER).getPath()
                    + File.separator + type.toString().toLowerCase() + ".html";
            File temaplateFile = new File(path);
            log.debug("Template path: {}", path);

            String stringTemplate = "";
            if (temaplateFile.exists()) {
                try {
                    List<String> lines = Files.readAllLines(Paths.get(temaplateFile.getCanonicalPath()));
                    for (String line : lines) {
                        stringBuilder.append(line);
                    }
                } catch (IOException e) {
                    log.error("Error while reading file", e);
                }
                stringTemplate = stringBuilder.toString();
            } else {
                throw new IllegalStateException("Template file not found");
            }
            return stringTemplate;
        }
    }

    public abstract void send(Map<String, Object> values) throws MessagingException;
}
