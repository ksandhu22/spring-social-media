package com.cooksys.twitterclone.repositories;

import com.cooksys.twitterclone.entities.Hashtag;
import com.cooksys.twitterclone.entities.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, String> {

    List<Tweet> findByTweets(String label);
}
