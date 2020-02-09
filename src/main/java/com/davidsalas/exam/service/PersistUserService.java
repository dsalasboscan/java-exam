package com.davidsalas.exam.service;

import com.davidsalas.exam.model.User;
import com.davidsalas.exam.model.UserMapper;
import com.davidsalas.exam.model.dto.ResponseDto;
import com.davidsalas.exam.model.dto.UserDto;
import com.davidsalas.exam.model.dto.UserDtoMapper;
import com.davidsalas.exam.repository.PersistUserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PersistUserService {

  private PersistUserRepository persistUserRepository;

  private UserDtoMapper userDtoMapper;

  private UserMapper userMapper;

  public PersistUserService(PersistUserRepository persistUserRepository, UserDtoMapper userDtoMapper, UserMapper userMapper) {
    this.persistUserRepository = persistUserRepository;
    this.userDtoMapper = userDtoMapper;
    this.userMapper = userMapper;
  }

  public ResponseDto createUser(UserDto userDto) {
    User user = userDtoMapper.mapToUser(userDto);
    user.setCreatedDate(LocalDateTime.now());
    user.setModifiedDate(LocalDateTime.now());
    user.setToken("nullForNow"); // TODO change
    return userMapper.mapToResponseDto(persistUserRepository.create(user));
  }
}