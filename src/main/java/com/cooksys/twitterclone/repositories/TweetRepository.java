package com.cooksys.twitterclone.repositories;


import com.cooksys.twitterclone.entities.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long>{

}
