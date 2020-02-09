package com.davidsalas.exam;

import com.davidsalas.exam.model.dto.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationTest {

  @Autowired
  private WebApplicationContext wac;

  @Autowired
  private ObjectMapper objectMapper;

  private MockMvc mockMvc;

  @Before
  public void setUp() {
    this.mockMvc =
        MockMvcBuilders.webAppContextSetup(this.wac).alwaysDo(MockMvcResultHandlers.print()).build();
  }

  @Test
  public void createUserOk() throws Exception {
    postRequest(getUserDtoAsString("pperez@mail.com", "Admin123#"))
        .andExpect(status().isCreated());
  }

  @Test
  public void createUserIncorrectEmailFormat() throws Exception {
    // If the email format is not 'aaaa@domain.com' then the user creation process fails with an error message

    postRequest(getUserDtoAsString("pperezmail.com", "Admin123#"))
        .andExpect(status().isBadRequest());

  }

  private String getUserDtoAsString(String email, String password) throws JsonProcessingException {
    return objectMapper.writeValueAsString(
        createUserDto(email, password)
    );
  }

  private UserDto createUserDto(String email, String password) {
    return new UserDto(
        "Pedro perez",
        email,
        password,
        createPhonesDto()
    );
  }

  private List<UserDto.PhoneDto> createPhonesDto() {
    List<UserDto.PhoneDto> phones = new ArrayList<>();
    phones.add(new UserDto.PhoneDto("69584354", "11", "54"));
    phones.add(new UserDto.PhoneDto("69584353", "11", "54"));
    phones.add(new UserDto.PhoneDto("69584356", "11", "54"));
    return phones;
  }

  private ResultActions postRequest(String content) throws Exception {
    return mockMvc.perform(
        post("/rest/v1/user/create")
            .contentType(MediaType.APPLICATION_JSON)
            .content(content));
  }


}