package com.davidsalas.exam.exception.custom;

import com.davidsalas.exam.exception.HttpException;
import org.springframework.http.HttpStatus;

public class PasswordInvalidFormatException extends HttpException {

  public PasswordInvalidFormatException(String message) {
    super(HttpStatus.BAD_REQUEST, message);
  }
}