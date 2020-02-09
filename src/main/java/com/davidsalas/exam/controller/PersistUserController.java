package com.davidsalas.exam.controller;

import com.davidsalas.exam.model.dto.ResponseDto;
import com.davidsalas.exam.model.dto.UserDto;
import com.davidsalas.exam.service.PersistUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PersistUserController {

  private PersistUserService persistUserService;

  public PersistUserController(@Autowired PersistUserService persistUserService) {
    this.persistUserService = persistUserService;
  }

  @PostMapping(
      value = "/rest/v1/user/create",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseDto createUser(@Valid @RequestBody UserDto userDto) {
    return persistUserService.createUser(userDto);
  }
}