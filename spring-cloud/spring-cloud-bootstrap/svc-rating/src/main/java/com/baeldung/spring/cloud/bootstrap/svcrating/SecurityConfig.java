package com.baeldung.spring.cloud.bootstrap.svcrating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal1(AuthenticationManagerBuilder auth) throws Exception {
        //try in memory auth with no users to support the case that this will allow for users that are logged in to go anywhere
        auth.inMemoryAuthentication();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
            .disable()
        .authorizeRequests()
            .antMatchers("/ratings").hasRole("USER")
            .antMatchers("/ratings/all").hasRole("ADMIN")
            .anyRequest().authenticated()
            .and()
        .csrf()
            .disable();
    }
}
