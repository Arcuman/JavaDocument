package com.arcuman.borto.services;

import com.arcuman.borto.models.User;
import lombok.extern.log4j.Log4j2;

import java.util.List;
public interface UserService {
  User register(User user);

  List<User> getAll();

  User findByUsername(String username);

  User findById(Long id);

  void delete(Long id);

  boolean isExistUsername(String username);

  boolean isExistEmail(String email);
}
