package com.prem.studyproject.config;


import com.prem.studyproject.domain.enums.Role;
import com.prem.studyproject.services.security.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/security.tld",
                        "/registration**",
                        "/css/**",
                        "/js/**",
                        "/spring/**",
                        "/fonts/**").permitAll()
                .antMatchers("/admin").hasAnyAuthority(Role.ADMIN.getAuthority())
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").usernameParameter("login").passwordParameter("password").permitAll()
                .defaultSuccessUrl("/")
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
