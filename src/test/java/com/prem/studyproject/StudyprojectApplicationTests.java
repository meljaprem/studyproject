package com.prem.studyproject;

import com.prem.studyproject.domain.enums.Role;
import com.prem.studyproject.domain.model.User;
import com.prem.studyproject.repository.interfaces.UserRepository;
import com.prem.studyproject.services.RegistrationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = StudyprojectApplication.class, loader = AnnotationConfigContextLoader.class)
public class StudyprojectApplicationTests {
    @Autowired
    private UserRepository repository;
    @Autowired
    private RegistrationService registrationService;

    @Test
    public void contextLoads() {
    }


    @Test
    public void testCreateAdmin() {
        List<Role> roleList = new ArrayList<>();
        roleList.add(Role.ADMIN);
        User user = registrationService.registerNewAdmin(
                User.builder()
                        .name("Dima")
                        .accountNonExpired(true)
                        .accountNonLocked(true)
                        .credentialsNonExpired(true)
                        .surname("Melnyk2")
                        .enabled(true)
                        .password("123")
                        .authorities(roleList)
                        .username("prem")
                        .build()
        );
        System.out.println("=====================================");
        System.out.println(user);
        System.out.println("=====================================");
    }

    @Test
    public void testRegUser() {
        User user = repository.save(
                User.builder()
                        .name("Dima")
                        .surname("Melnyk")
                        .password("123")
                        .username("prem" + (int) ((Math.random() * 10000)) + 1)
                        .email("asdaw@asda.ua" + (int) ((Math.random() * 100000)) + 1)
                        .build()
        );
        User registratedUser = registrationService.registerNewUser(user);
    }


}
