package com.davidsalas.exam.security;

public class SecurityConstants {
  public static final String SECRET = "SecretKeyToGenJWTs";
  public static final long EXPIRATION_TIME = 864_000_000; // 10 days
  public static final String SIGN_UP_URL = "/rest/v1/user/register";
  public static final String H2_CONSOLE_ENDPOINT = "/h2-console";
}