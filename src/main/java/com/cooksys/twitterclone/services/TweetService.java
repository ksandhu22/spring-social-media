package com.cooksys.twitterclone.services;

import java.util.List;

import com.cooksys.twitterclone.dtos.TweetResponseDto;
import com.cooksys.twitterclone.dtos.UserResponseDto;
import com.cooksys.twitterclone.exceptions.NotFoundException;

public interface TweetService {

    List<TweetResponseDto> getAllTweets();

//	TweetResponseDto createTweet(TweetRequestDto newTweet);

    TweetResponseDto getTweetById(Long id) throws NotFoundException;

    List<UserResponseDto> getUsersByTweetId(Long id) throws NotFoundException;

}
