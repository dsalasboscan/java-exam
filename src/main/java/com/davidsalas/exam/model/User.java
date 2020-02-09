package com.davidsalas.exam.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity(name = "user")
@Data
@NoArgsConstructor
public class User {

  public User(String name, String email, String password, List<Phone> phones, boolean isActive) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.phones = phones;
    this.isActive = isActive;
  }

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
      name = "UUID",
      strategy = "org.hibernate.id.UUIDGenerator"
  )
  private UUID id;

  private String name;

  private String email;

  private String password;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
  private List<Phone> phones;

  @Column(name = "creation_date", columnDefinition = "TIMESTAMP")
  private LocalDateTime createdDate;

  @Column(name = "modification_date", columnDefinition = "TIMESTAMP")
  private LocalDateTime modifiedDate;

  @Column(name = "last_login_date", columnDefinition = "TIMESTAMP")
  private LocalDateTime lastLoginDate;

  private String token;

  @Column(name = "is_active")
  private boolean isActive;
}
