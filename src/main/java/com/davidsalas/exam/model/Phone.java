package com.davidsalas.exam.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "phone")
@Data
@NoArgsConstructor
public class Phone {

  public Phone(String number, String cityCode, String countryCode) {
    this.number = number;
    this.cityCode = cityCode;
    this.countryCode = countryCode;
  }

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
      name = "UUID",
      strategy = "org.hibernate.id.UUIDGenerator"
  )
  private UUID id;

  private String number;

  @Column(name = "city_code")
  private String cityCode;

  @Column(name = "country_code")
  private String countryCode;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;
}