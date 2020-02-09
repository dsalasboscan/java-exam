package com.davidsalas.exam.controller;

import com.davidsalas.exam.model.dto.Response;
import com.davidsalas.exam.model.dto.UserDto;
import com.davidsalas.exam.service.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class RegisterUserController {

  private RegisterUserService registerUserService;

  public RegisterUserController(@Autowired RegisterUserService registerUserService) {
    this.registerUserService = registerUserService;
  }

  @PostMapping(
      value = "/rest/v1/user/register",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<Response> registerUser(@Valid @RequestBody UserDto userDto) {
    return registerUserService.register(userDto).returnHttpResponse(HttpStatus.CREATED);
  }
}