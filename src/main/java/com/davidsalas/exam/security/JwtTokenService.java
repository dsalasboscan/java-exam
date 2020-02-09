package com.davidsalas.exam.security;

import com.auth0.jwt.JWT;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.davidsalas.exam.security.SecurityConstants.EXPIRATION_TIME;
import static com.davidsalas.exam.security.SecurityConstants.SECRET;

@Service
public class JwtTokenService {

  public String createToken(String userName) {
    return JWT.create()
        .withSubject(userName)
        .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .sign(HMAC512(SECRET.getBytes()));
  }
}