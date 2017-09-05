package com.prem.studyproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableAsync
@SpringBootApplication
public class StudyprojectApplication  {
	public static void main(String[] args) {
		SpringApplication.run(StudyprojectApplication.class, args);
	}
}
