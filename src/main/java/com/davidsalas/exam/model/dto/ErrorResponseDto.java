package com.davidsalas.exam.model.dto;

import lombok.Data;

@Data
public class ErrorResponseDto implements Response {
  private String message;

  public ErrorResponseDto(String message) {
    this.message = message;
  }
}