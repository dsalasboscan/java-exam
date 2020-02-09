package com.davidsalas.exam.repository;

import com.davidsalas.exam.exception.custom.EmailAlreadyExistException;
import com.davidsalas.exam.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public class RegisterUserRepository {

  private SessionFactory sessionFactory;

  private JdbcTemplate jdbcTemplate;

  private static final String GET_USER_BY_EMAIL_QUERY = "SELECT count(*) FROM USER WHERE email = ?";

  @Autowired
  public RegisterUserRepository(SessionFactory sessionFactory, JdbcTemplate jdbcTemplate) {
    this.sessionFactory = sessionFactory;
    this.jdbcTemplate = jdbcTemplate;
  }

  @Transactional
  public User persist(User user) {
    Session session = sessionFactory.getCurrentSession();
    session.persist(user);
    return user;
  }

  @Transactional
  public void checkIfEmailExist(String email) {
    Integer count = jdbcTemplate.queryForObject(GET_USER_BY_EMAIL_QUERY, new Object[]{email}, Integer.class);

    if (count == null) {
      return;
    }

    if (count.equals(1)) {
      throw new EmailAlreadyExistException(email);
    }
  }
}