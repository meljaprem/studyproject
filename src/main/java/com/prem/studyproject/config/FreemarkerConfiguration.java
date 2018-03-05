package com.prem.studyproject.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
@Slf4j
public class FreemarkerConfiguration  extends FreeMarkerAutoConfiguration.FreeMarkerWebConfiguration{

    @Override
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer configurer = super.freeMarkerConfigurer();

        Map<String, Object> sharedVariables = new HashMap<>();
        try {
            Properties messagesProperties = getMessagesProperties();
            for (Map.Entry<Object, Object> entry : messagesProperties.entrySet()) {
                sharedVariables.put(entry.getKey().toString(), entry.getValue());
            }
        } catch (IOException e) {
            log.error("IOException while getting messages properties", e);
        }
        log.debug(sharedVariables.size() + " properies shared with FreeMarker");
        log.trace("They are : " + sharedVariables);
        configurer.setFreemarkerVariables(sharedVariables);

        return configurer;
    }

    private Properties getMessagesProperties() throws IOException {
        Resource resource =  new ClassPathResource("/messages.properties");
        Properties props = PropertiesLoaderUtils.loadProperties(resource);
        return props;
    }
}
