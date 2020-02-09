package com.davidsalas.exam.model.dto;

import com.davidsalas.exam.model.dto.validation.ValidEmail;
import com.davidsalas.exam.model.dto.validation.ValidPassword;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {

  private String name;

  @ValidEmail
  private String email;

  @ValidPassword
  private String password;

  private List<PhoneDto> phones;

  public UserDto(String name, String email, String password, List<PhoneDto> phones) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.phones = phones;
  }

  @Data
  public static final class PhoneDto {
    private String number;
    private String cityCode;
    private String countryCode;

    public PhoneDto(String number, String cityCode, String countryCode) {
      this.number = number;
      this.cityCode = cityCode;
      this.countryCode = countryCode;
    }
  }
}