package com.secure.peaas.repository;

import com.secure.peaas.entity.Tweet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by grahul on 17-11-2018.
 */
@Repository
public interface TweetRepository extends CrudRepository<Tweet, Long> {

  Page<Tweet> findByUserId(Long userId, Pageable pageable);
}
