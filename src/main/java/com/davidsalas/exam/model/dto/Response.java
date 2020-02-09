package com.davidsalas.exam.model.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface Response {

  default ResponseEntity<Response> returnHttpResponse(HttpStatus httpStatus) {
    return new ResponseEntity<>(this, httpStatus);
  }
}