package com.cooksys.twitterclone.repositories;


import com.cooksys.twitterclone.entities.Tweet;
import com.cooksys.twitterclone.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long>{
    List<User> findByLikedByUsers(Long tweetId);


//    List<Tweet> findByTweets(String label);

//    List<User> findByLikedByUsersAndDeletedFalse(Long tweetId);

//    List<User> findByLikedByUsersAndDeletedFalse(List<User> users, Boolean deleted);

//    List<User> findByLikedByUsersAndDeleted(List<User> users, Boolean deleted);

    //List<User> findByLikedByUsersAndDeletedFalse(Long id);

//    List<User> findByNameOrAge(String name, Integer age);
//    List<User> findByNameAndActive(String name, Boolean active);
    
    

}
