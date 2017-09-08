package com.prem.studyproject.config;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ConfigurationProperties("mongodb")
@Slf4j
public class MongoConfig {
    private String host;
    private Integer port;
    private String user;
    private String pass;
    private String database;

    Environment environment;


    public @Bean
    MongoDbFactory mongoDbFactory2() throws Exception {
        this.toString();
        String prefix = "mongodb://";
        String connectionURI = host + ":" + port + "/" + database;
        String auth = user + ":" + pass + "@";
        MongoClientURI uri;
        if (host.equals("localhost")) {
            uri = new MongoClientURI(prefix + connectionURI);
        } else {
            uri = new MongoClientURI(prefix + auth + connectionURI);
        }
        log.info("Connected to MongoDB URI: " + uri.toString());
        return new SimpleMongoDbFactory(uri);
    }

    public @Bean
    MongoDbFactory mongoDbFactory() throws Exception {
        return new SimpleMongoDbFactory(new MongoClient(host, port), database);
    }


    public @Bean
    MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
        return mongoTemplate;
    }
}
