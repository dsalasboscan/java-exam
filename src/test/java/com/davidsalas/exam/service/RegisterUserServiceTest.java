package com.davidsalas.exam.service;

import com.davidsalas.exam.exception.custom.EmailAlreadyExistException;
import com.davidsalas.exam.model.User;
import com.davidsalas.exam.model.UserMapper;
import com.davidsalas.exam.model.dto.ResponseDto;
import com.davidsalas.exam.model.dto.UserDto;
import com.davidsalas.exam.model.dto.UserDtoMapper;
import com.davidsalas.exam.repository.RegisterUserRepository;
import com.davidsalas.exam.security.JwtTokenService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RegisterUserServiceTest {

  @Mock
  RegisterUserRepository registerUserRepository;

  @Mock
  JwtTokenService jwtTokenService;

  RegisterUserService registerUserService;

  UserDtoMapper userDtoMapper;

  BCryptPasswordEncoder bCryptPasswordEncoder;

  UserMapper userMapper;

  @Before
  public void init() {
    userDtoMapper = new UserDtoMapper();
    userMapper = new UserMapper();
    bCryptPasswordEncoder = new BCryptPasswordEncoder();
    registerUserService = new RegisterUserService(registerUserRepository, userDtoMapper, userMapper, jwtTokenService, bCryptPasswordEncoder);
  }

  @Test
  public void registerUserOk() {
    String uuid = "5da8907f-4eec-43c4-b987-8ac8dfa028e2";
    String mokedToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJKb3NlIFBlcmV6IiwiZXhwIjoxNTgyMTcxMjM2fQ.UpE_r6jqzo9fKH_hAjbAaZxBaerXGC3qq3NhSZkFw7OqOrPRUYWSCOJ9cT_rFQWRICrZlzkHB1MOLfD46uctbw";

    User user = createUser(mokedToken, uuid);

    when(registerUserRepository.persist(any(User.class))).thenReturn(user);
    when(jwtTokenService.createToken(user.getName())).thenReturn(mokedToken);

    UserDto userDto = createUserDto();

    ResponseDto response = registerUserService.register(userDto);

    Assert.assertEquals(uuid, response.getId());
    Assert.assertEquals(mokedToken, response.getToken());
    Assert.assertEquals(uuid, response.getId());
    Assert.assertTrue(response.isActive());
  }

  @Test(expected = EmailAlreadyExistException.class)
  public void emailAlreadyExistsError() {
    doThrow(new EmailAlreadyExistException("jperez@gmail.com")).when(registerUserRepository).checkIfEmailExist("jperez@gmail.com");
    registerUserService.register(createUserDto());
  }

  private UserDto createUserDto() {
    return new UserDto(
        "Jose Perez",
        "jperez@gmail.com",
        "A1234",
        Collections.singletonList(new UserDto.PhoneDto("20405060", "11", "54"))
    );
  }

  private User createUser(String token, String uuid) {
    User user = userDtoMapper.mapToUser(createUserDto());
    user.setToken(token);
    user.setId(UUID.fromString(uuid));
    return user;
  }
}