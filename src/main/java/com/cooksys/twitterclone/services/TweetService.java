package com.cooksys.twitterclone.services;

import com.cooksys.twitterclone.dtos.TweetRequestDto;
import com.cooksys.twitterclone.dtos.TweetResponseDto;
import com.cooksys.twitterclone.entities.Tweet;

import java.util.List;

public interface TweetService {

    List<TweetResponseDto> getAllTweets();

	TweetResponseDto createTweet(TweetRequestDto newTweet);
}
