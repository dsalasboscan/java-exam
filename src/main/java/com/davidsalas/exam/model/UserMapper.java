package com.davidsalas.exam.model;

import com.davidsalas.exam.model.dto.ResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public ResponseDto mapToResponseDto(User user) {
    return new ResponseDto(
        user.getId().toString(),
        user.getCreatedDate(),
        user.getModifiedDate(),
        user.getLastLoginDate(),
        user.getToken(),
        user.isActive()
    );
  }
}