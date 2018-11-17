package com.secure.peaas.service;

import com.secure.peaas.entity.Tweet;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by grahul on 17-11-2018.
 */
public interface TweetDao {

  void createTweet(Tweet tweet);

  List<Tweet> getTweet();

  Optional<Tweet> findById(long id);

  Tweet update(Tweet tweet, long l);

  void deleteTweetById(long id);

  Tweet updatePartially(Tweet tweet, long id);

  Page<Tweet> findByUserId(Long postId, Pageable pageable);
}
