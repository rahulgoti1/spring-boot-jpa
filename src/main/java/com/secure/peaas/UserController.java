package com.secure.peaas;

import com.secure.peaas.entity.User;
import com.secure.peaas.service.UserDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v1")
public class UserController {

  @Autowired
  UserDao userService;

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
    System.out.println("Fetching User with id " + id);
    User user = userService.findById(id).orElse(null);
    if (user == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @PostMapping(value = "/create", headers = "Accept=application/json")
  public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
    System.out.println("Creating User " + user.getUserName());
    userService.createUser(user);
    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
    return new ResponseEntity<>(headers, HttpStatus.CREATED);
  }

  @GetMapping(value = "/get", headers = "Accept=application/json")
  public List<User> getAllUser() {
    List<User> tasks = userService.getUser();
    return tasks;

  }

  @PutMapping(value = "/update", headers = "Accept=application/json")
  public ResponseEntity<String> updateUser(@RequestBody User currentUser) {
    System.out.println("sd");
    User user = userService.findById(currentUser.getId()).orElse(null);
    if (user == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    userService.update(currentUser, currentUser.getId());
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping(value = "/{id}", headers = "Accept=application/json")
  public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
    User user = userService.findById(id).orElse(null);
    if (user == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    userService.deleteUserById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PatchMapping(value = "/{id}", headers = "Accept=application/json")
  public ResponseEntity<User> updateUserPartially(@PathVariable("id") long id, @RequestBody User currentUser) {
    User user = userService.findById(id).orElse(null);
    if (user == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    User usr = userService.updatePartially(currentUser, id);
    return new ResponseEntity<>(usr, HttpStatus.OK);
  }

}