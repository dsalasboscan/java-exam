package com.davidsalas.exam.repository;

import com.davidsalas.exam.model.Phone;
import com.davidsalas.exam.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegisterUserRepositoryTest {

  @Autowired
  RegisterUserRepository registerUserRepository;

  @Test
  public void testPersistOk() {
    LocalDateTime localDateTime = LocalDateTime.now();
    User user = createUser(localDateTime);

    User persistedUser = registerUserRepository.persist(user);

    Assert.assertNotNull(persistedUser.getId());
  }

  private User createUser(LocalDateTime localDateTime) {
    User user = new User(
        "jose canseco",
        "jcanseco@mail.com",
        "123asd",
        Collections.singletonList(new Phone("20405060", "11", "54")),
        true
    );
    user.setLastLoginDate(localDateTime);
    user.setModifiedDate(localDateTime);
    user.setCreatedDate(localDateTime);
    user.setToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJKb3NlIFBlcmV6IiwiZXhwIjoxNTgyMTcxMjM2fQ.UpE_r6jqzo9fKH_hAjbAaZxBaerXGC3qq3NhSZkFw7OqOrPRUYWSCOJ9cT_rFQWRICrZlzkHB1MOLfD46uctbw");
    return user;
  }
}