package com.secure.peaas.controller;

import com.secure.peaas.entity.Tweet;
import com.secure.peaas.exception.ResourceNotFoundException;
import com.secure.peaas.service.TweetDao;
import com.secure.peaas.service.UserDao;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TweetController {

  @Autowired
  UserDao userDao;

  @Autowired
  TweetDao tweetDao;

  @GetMapping(value = "/users/{id}/tweets", produces = MediaType.APPLICATION_JSON_VALUE)
  public Page<Tweet> getAllTweetsByUserId(@PathVariable(value = "id") Long userId,
                                          Pageable pageable) {
    return tweetDao.findByUserId(userId, pageable);
  }

  @PostMapping("/users/{id}/tweets")
  public Tweet createTweet(@PathVariable(value = "id") Long userId,
                           @Valid @RequestBody Tweet tweet) {
    return userDao.findById(userId).map(post -> {
      tweet.setUser(post);
      tweetDao.createTweet(tweet);
      return tweet;
    }).orElseThrow(() -> new ResourceNotFoundException("User " + userId + " not found"));
  }

  @PutMapping("/users/{id}/tweets/{tweetId}")
  public Tweet updateComment(@PathVariable(value = "id") Long userId,
                             @PathVariable(value = "tweetId") Long tweetId,
                             @Valid @RequestBody Tweet tweet) {
    if (!userDao.findById(userId).isPresent()) {
      throw new ResourceNotFoundException("User " + userId + " not found");
    }

    return tweetDao.findById(tweetId).map(comment -> {
      comment.setText(tweet.getText());
      tweetDao.createTweet(comment);
      return comment;
    }).orElseThrow(() -> new ResourceNotFoundException("Tweet " + tweetId + "not found"));
  }

  @DeleteMapping("/users/{id}/tweets/{tweetId}")
  public ResponseEntity<?> deleteComment(@PathVariable(value = "id") Long userId,
                                         @PathVariable(value = "tweetId") Long tweetId) {
    if (!userDao.findById(userId).isPresent()) {
      throw new ResourceNotFoundException("User " + userId + " not found");
    }

    return tweetDao.findById(tweetId).map(comment -> {
      tweetDao.deleteTweetById(comment.getId());
      return ResponseEntity.ok().build();
    }).orElseThrow(() -> new ResourceNotFoundException("Tweet " + tweetId + " not found"));
  }

}
