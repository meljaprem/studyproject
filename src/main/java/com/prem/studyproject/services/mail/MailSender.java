package com.prem.studyproject.services.mail;

import com.prem.studyproject.domain.enums.TemplateType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@Service
@PropertySource("classpath:application.properties")
@Slf4j
public abstract class MailSender {

    private static final String FOLDER = "mailTemplates";

    public String getTemplate(TemplateType type) {
        log.debug("getTemplate method invoke : TemplateType : {}, FOLDER : {} ", type, FOLDER);
        String path = getClass().getClassLoader().getResource(FOLDER).getPath()
                + File.separator + type.toString().toLowerCase() + ".html";
        File temaplateFile = new File(path);
        log.debug("Template path: {}", path);
        StringBuilder stringBuilder = new StringBuilder();
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

    @Async
    public abstract void send(Map<String, Object> values);
}
