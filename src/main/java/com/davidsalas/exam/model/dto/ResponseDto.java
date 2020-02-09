package com.davidsalas.exam.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseDto implements Response {
  private String uuid;
  private LocalDateTime created;
  private LocalDateTime modified;
  private LocalDateTime lastLogin;
  private String token;
  private boolean isActive;

  public ResponseDto(String uuid, LocalDateTime created, LocalDateTime modified, LocalDateTime lastLogin, String token, boolean isActive) {
    this.uuid = uuid;
    this.created = created;
    this.modified = modified;
    this.lastLogin = lastLogin;
    this.token = token;
    this.isActive = isActive;
  }
}