package com.secure.peaas.service;

import com.secure.peaas.entity.User;
import com.secure.peaas.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by grahul on 17-11-2018.
 */
@Service
@Transactional
public class UserServiceImpl implements UserDao {

  private final UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public void createUser(User user) {
    userRepository.save(user);
  }

  @Override
  public List<User> getUser() {
    return (List<User>) userRepository.findAll();
  }

  @Override
  public Optional<User> findById(long id) {
    return userRepository.findById(id);
  }

  @Override
  public User update(User user, long l) {
    return userRepository.save(user);
  }

  @Override
  public void deleteUserById(long id) {
    userRepository.deleteById(id);
  }

  @Override
  public User updatePartially(User user, long id) {
    Optional<User> oneUser = userRepository.findById(id);
    oneUser.ifPresent(user1 -> user.setEmail(user1.getEmail()));
    return userRepository.save(user);
  }
}
