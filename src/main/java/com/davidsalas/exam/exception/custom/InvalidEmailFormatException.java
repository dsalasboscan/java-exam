package com.davidsalas.exam.exception.custom;

import com.davidsalas.exam.exception.HttpException;
import org.springframework.http.HttpStatus;

public class InvalidEmailFormatException extends HttpException {

  public InvalidEmailFormatException(String wrongEmail) {
    super(HttpStatus.BAD_REQUEST, "The format of email " + wrongEmail + " is invalid, it should have aaaa@mail.ve style");
  }
}