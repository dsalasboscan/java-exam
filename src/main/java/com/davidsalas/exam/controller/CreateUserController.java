package com.davidsalas.exam.controller;

import com.davidsalas.exam.model.dto.Response;
import com.davidsalas.exam.model.dto.UserDto;
import com.davidsalas.exam.service.CreateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CreateUserController {

  private CreateUserService createUserService;

  public CreateUserController(@Autowired CreateUserService createUserService) {
    this.createUserService = createUserService;
  }

  @PostMapping(
      value = "/rest/v1/user/create",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<Response> createUser(@Valid @RequestBody UserDto userDto) {
    return createUserService.createUser(userDto).returnHttpResponse(HttpStatus.CREATED);
  }
}