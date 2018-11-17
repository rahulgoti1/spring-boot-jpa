package com.secure.peaas.service;

import com.secure.peaas.entity.Tweet;
import com.secure.peaas.repository.TweetRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by grahul on 17-11-2018.
 */
@Service
@Transactional
public class TweetServiceImpl implements TweetDao {

  private final TweetRepository tweetRepository;

  public TweetServiceImpl(TweetRepository tweetRepository) {
    this.tweetRepository = tweetRepository;
  }

  @Override
  public void createTweet(Tweet user) {
    tweetRepository.save(user);
  }

  @Override
  public List<Tweet> getTweet() {
    return (List<Tweet>) tweetRepository.findAll();
  }

  @Override
  public Optional<Tweet> findById(long id) {
    return tweetRepository.findById(id);
  }

  @Override
  public Tweet update(Tweet tweet, long l) {
    return tweetRepository.save(tweet);
  }

  @Override
  public void deleteTweetById(long id) {
    tweetRepository.deleteById(id);
  }

  @Override
  public Tweet updatePartially(Tweet user, long id) {
    Optional<Tweet> oneUser = tweetRepository.findById(id);
    oneUser.ifPresent(user1 -> user.setText(user1.getText()));
    return tweetRepository.save(user);
  }

  @Override
  public Page<Tweet> findByUserId(Long userId, Pageable pageable) {
    return tweetRepository.findByUserId(userId, pageable);
  }
}
