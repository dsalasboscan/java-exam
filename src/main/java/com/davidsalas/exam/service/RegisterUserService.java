package com.davidsalas.exam.service;

import com.davidsalas.exam.model.User;
import com.davidsalas.exam.model.UserMapper;
import com.davidsalas.exam.model.dto.ResponseDto;
import com.davidsalas.exam.model.dto.UserDto;
import com.davidsalas.exam.model.dto.UserDtoMapper;
import com.davidsalas.exam.repository.RegisterUserRepository;
import com.davidsalas.exam.security.JwtTokenService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RegisterUserService {

  private RegisterUserRepository registerUserRepository;

  private UserDtoMapper userDtoMapper;

  private UserMapper userMapper;

  private BCryptPasswordEncoder passwordEncoder;

  private JwtTokenService jwtTokenService;

  public RegisterUserService(RegisterUserRepository registerUserRepository,
                             UserDtoMapper userDtoMapper,
                             UserMapper userMapper,
                             JwtTokenService jwtTokenService,
                             BCryptPasswordEncoder passwordEncoder
  ) {
    this.registerUserRepository = registerUserRepository;
    this.userDtoMapper = userDtoMapper;
    this.userMapper = userMapper;
    this.jwtTokenService = jwtTokenService;
    this.passwordEncoder = passwordEncoder;
  }

  public ResponseDto register(UserDto userDto) {
    User user = userDtoMapper.mapToUser(userDto);

    encodePassword(user);

    user.setCreatedDate(LocalDateTime.now());
    user.setModifiedDate(LocalDateTime.now());
    user.setLastLoginDate(LocalDateTime.now());
    user.setToken(jwtTokenService.createToken(user.getName()));

    return userMapper.mapToResponseDto(registerUserRepository.persist(user));
  }

  private void encodePassword(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
  }
}