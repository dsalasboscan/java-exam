package com.davidsalas.exam.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public abstract class HttpException extends RuntimeException {
  private HttpStatus httpStatus;
  private String message;

  public HttpException(HttpStatus httpStatus, String message) {
    this.httpStatus = httpStatus;
    this.message = message;
  }
}