package com.prem.studyproject.config;


import com.prem.studyproject.domain.enums.Role;
import com.prem.studyproject.services.security.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final static String LOCKED_ACCOUNT_URL = "/login?error=locked";
    private final static String DISABLED_ACCOUNT_URL = "/login?error=deactivated";
    private final static String WRONG_ACCOUNT_URL = "/login?error=wrong";

    @Autowired
    @Qualifier("loginFailerHandler")
    public void setHandler(AuthenticationFailureHandler handler) {
        this.handler = handler;
    }

    private AuthenticationFailureHandler handler;

    @Bean("loginFailerHandler")
    public AuthenticationFailureHandler getAuthenticationFailureHandler(){
        ExceptionMappingAuthenticationFailureHandler errorhandler = new ExceptionMappingAuthenticationFailureHandler();
        Map<String, String> excMap = new HashMap<>();
        excMap.put("org.springframework.security.authentication.DisabledException", DISABLED_ACCOUNT_URL);
        excMap.put("org.springframework.security.authentication.LockedException", LOCKED_ACCOUNT_URL);
        excMap.put("org.springframework.security.authentication.BadCredentialsException", WRONG_ACCOUNT_URL);
        errorhandler.setExceptionMappings(excMap);
        return errorhandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/security.tld",
                        "/registration/**",
                        "/login**",
                        "/css/**",
                        "/js/**",
                        "/spring/**",
                        "/fonts/**").permitAll()
                .antMatchers("/admin").hasAnyAuthority(Role.ADMIN.getAuthority())
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").usernameParameter("login").passwordParameter("password").permitAll()
                .defaultSuccessUrl("/")
                .failureHandler(handler)
                .failureUrl("/login?error")
                .and()
                .csrf().disable()
                .logout().permitAll();
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth, UserSecurityService userSecurityService) throws Exception {
        auth
                .userDetailsService(userSecurityService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

}
