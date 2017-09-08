package com.prem.studyproject.config;


import com.mongodb.MongoClientURI;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

@Configuration
@Data
@NoArgsConstructor
@ToString
@Slf4j
public class MongoConfig {
    @Value("${DB_HOST}")
    private String host;
    @Value("${DB_PORT}")
    private String port;
    @Value("${DB_USER}")
    private String user;
    @Value("${DB_PASSWORD}")
    private String pass;
    @Value("${DB_NAME}")
    private String database;


    public @Bean
    MongoDbFactory mongoDbFactory(
    ) throws Exception {
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
    MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
        return mongoTemplate;
    }

//    public  static @Bean
//    PropertyPlaceholderConfigurer mongoPropertyPlaceholderConfigurer() throws Exception {
//        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
//        propertyPlaceholderConfigurer.setSearchSystemEnvironment(true);
//        propertyPlaceholderConfigurer.setSystemPropertiesModeName("SYSTEM_PROPERTIES_MODE_OVERRIDE");
//        return propertyPlaceholderConfigurer;
//    }
}
