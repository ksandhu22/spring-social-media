package com.cooksys.twitterclone.services;

import com.cooksys.twitterclone.dtos.TweetResponseDto;
import com.cooksys.twitterclone.dtos.UserResponseDto;
import com.cooksys.twitterclone.entities.Tweet;
import com.cooksys.twitterclone.exceptions.NotFoundException;

import java.util.List;

public interface TweetService {

    List<TweetResponseDto> getAllTweets();

    TweetResponseDto getTweetById(Long id) throws NotFoundException;

    List<UserResponseDto> getUsersByTweetId(Long id) throws NotFoundException;
}
