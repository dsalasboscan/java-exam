package com.davidsalas.exam.model.dto;

import com.davidsalas.exam.model.Phone;
import com.davidsalas.exam.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoMapper {

  public User mapToUser(UserDto userDto) {
    return new User(
        userDto.getName(),
        userDto.getEmail(),
        userDto.getPassword(),
        mapPhones(userDto.getPhones()),
        true

    );
  }

  private List<Phone> mapPhones(List<UserDto.PhoneDto> phonesDto) {
    return phonesDto
        .stream()
        .map(phoneDto -> new Phone(phoneDto.getNumber(), phoneDto.getCityCode(), phoneDto.getCountryCode()))
        .collect(Collectors.toList());
  }
}