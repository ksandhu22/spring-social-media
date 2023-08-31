package com.cooksys.twitterclone.repositories;


import com.cooksys.twitterclone.entities.Tweet;
import com.cooksys.twitterclone.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long>{
//    List<User> findByLikedByUsers(Long tweetId);
//    List<User> findByLikedByUsersAndDeletedFalse(Long tweetId);

//    List<User> findByLikedByUsersAndDeletedFalse(List<User> users, Boolean deleted);

//    List<User> findByLikedByUsersAndDeleted(List<User> users, Boolean deleted);

    List<User> findByLikedByUsersAndDeletedFalse(Long id);

//    List<User> findByNameOrAge(String name, Integer age);
//    List<User> findByNameAndActive(String name, Boolean active);


}
