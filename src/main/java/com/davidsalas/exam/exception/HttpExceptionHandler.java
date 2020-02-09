package com.davidsalas.exam.exception;

import com.davidsalas.exam.model.dto.ErrorResponseDto;
import com.davidsalas.exam.model.dto.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HttpExceptionHandler {

  @ExceptionHandler
  public ResponseEntity<Response> handleHttpException(HttpException e) {
    return new ErrorResponseDto(e.getMessage()).returnHttpResponse(e.getHttpStatus());
  }
}