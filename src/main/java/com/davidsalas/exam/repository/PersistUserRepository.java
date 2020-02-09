package com.davidsalas.exam.repository;

import com.davidsalas.exam.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public class PersistUserRepository {

  private SessionFactory sessionFactory;

  @Autowired
  public PersistUserRepository(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Transactional
  public User create(User user) {
    Session session = sessionFactory.getCurrentSession();
    session.persist(user);
    return user;
  }
}