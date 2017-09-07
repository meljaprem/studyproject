package com.prem.studyproject.config;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ConfigurationProperties("mongodb")
@PropertySource("classpath:application.properties")
@Slf4j
public class MongoConfig {
    private String host;
    private Integer port;
    private String user;
    private String pass;
    private String database;

    @Profile("work")
    public @Bean
    MongoDbFactory mongoDbFactory() throws Exception {
        return new SimpleMongoDbFactory(new MongoClient(host, port), database);
    }


    @Profile({"home"})
    public @Bean
    MongoDbFactory mongoDbFactory2() throws Exception {
        MongoClientURI uri = new MongoClientURI("mongodb://" + user + ":" + pass + "@" + host + ":" + port + "/" + database);
        log.info("Connected to MongoDB URI: " + uri.toString());
        return new SimpleMongoDbFactory(uri);
    }

    public @Bean
    MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
        return mongoTemplate;
    }
}
