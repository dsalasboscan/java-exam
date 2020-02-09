package com.davidsalas.exam.exception.custom;

import com.davidsalas.exam.exception.HttpException;
import org.springframework.http.HttpStatus;

public class EmailAlreadyExistException extends HttpException {

  public EmailAlreadyExistException(String email) {
    super(HttpStatus.CONFLICT, "Email: " + email + " already exist on the system");
  }
}