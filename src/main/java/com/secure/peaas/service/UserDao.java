package com.secure.peaas.service;

import com.secure.peaas.entity.User;
import java.util.List;
import java.util.Optional;

/**
 * Created by grahul on 17-11-2018.
 */
public interface UserDao {

  void createUser(User user);

  List<User> getUser();

  Optional<User> findById(long id);

  User update(User user, long l);

  void deleteUserById(long id);

  User updatePartially(User user, long id);

}
