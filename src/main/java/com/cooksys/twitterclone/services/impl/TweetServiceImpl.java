package com.cooksys.twitterclone.services.impl;

import com.cooksys.twitterclone.dtos.TweetResponseDto;
import com.cooksys.twitterclone.dtos.UserResponseDto;
import com.cooksys.twitterclone.entities.Tweet;
import com.cooksys.twitterclone.entities.User;
import com.cooksys.twitterclone.exceptions.NotFoundException;
import com.cooksys.twitterclone.mappers.TweetMapper;
import com.cooksys.twitterclone.mappers.UserMapper;
import com.cooksys.twitterclone.repositories.TweetRepository;
import org.springframework.stereotype.Service;

import com.cooksys.twitterclone.services.TweetService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {

    private final TweetRepository tweetRepository;
    private final TweetMapper tweetMapper;
    private final UserMapper userMapper;

    @Override
    public List<TweetResponseDto> getAllTweets() {
        return tweetMapper.entitiesToDtos(tweetRepository.findAll().stream().filter(tweet -> !tweet.isDeleted()).toList());
    }

    @Override
    public TweetResponseDto getTweetById(Long id) throws NotFoundException{

        Optional<Tweet> foundTweet = tweetRepository.findById(id);

        if(foundTweet.isEmpty() || foundTweet.get().isDeleted()) {
            throw new NotFoundException("Tweet not found");
        }

        return tweetMapper.entityToDto(foundTweet.get());
    }

    @Override
    public List<UserResponseDto> getUsersByTweetId(Long id) throws NotFoundException {

//        Deleted users should be excluded from the response.

        Optional<Tweet> foundTweet = tweetRepository.findById(id);

        if(foundTweet.isEmpty() || foundTweet.get().isDeleted()) {
            throw new NotFoundException("Tweet not found");
        }

        // List<User> foundUsers = tweetRepository.findByLikedByUsersAndDeleted(foundTweet.get().getLikedByUsers(), false);
        // List<User> foundUsers = tweetRepository.findByLikedByUsersAndDeletedFalse(id);

        return userMapper.entitiesToDtos(foundTweet.get().getLikedByUsers().stream().filter(user -> !user.isDeleted()).toList());
    }
}
