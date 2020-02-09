package com.davidsalas.exam.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseDto implements Response {
  private String id;

  private LocalDateTime created;

  private LocalDateTime modified;

  @JsonProperty("last_login")
  private LocalDateTime lastLogin;

  private String token;

  @JsonProperty("is_active")
  private boolean isActive;

  public ResponseDto(String id, LocalDateTime created, LocalDateTime modified, LocalDateTime lastLogin, String token, boolean isActive) {
    this.id = id;
    this.created = created;
    this.modified = modified;
    this.lastLogin = lastLogin;
    this.token = token;
    this.isActive = isActive;
  }
}