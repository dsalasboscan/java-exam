package com.davidsalas.exam.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static com.davidsalas.exam.security.SecurityConstants.H2_CONSOLE_ENDPOINT;
import static com.davidsalas.exam.security.SecurityConstants.SIGN_UP_URL;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.headers().frameOptions().disable()
        .and().csrf().disable().authorizeRequests()
        .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
        .antMatchers(H2_CONSOLE_ENDPOINT).permitAll();
  }
}