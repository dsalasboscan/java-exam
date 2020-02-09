package com.davidsalas.exam.exception.custom;

import com.davidsalas.exam.exception.HttpException;
import org.springframework.http.HttpStatus;

public class InvalidPasswordException extends HttpException {

  public InvalidPasswordException(String message) {
    super(HttpStatus.BAD_REQUEST, message);
  }
}