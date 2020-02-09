package com.davidsalas.exam.model.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import java.util.List;

@Data
public class UserDto {

  private String name;

  @Email
  private String email;

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