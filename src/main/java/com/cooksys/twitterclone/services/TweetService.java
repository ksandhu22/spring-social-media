package com.cooksys.twitterclone.services;

import com.cooksys.twitterclone.dtos.TweetResponseDto;
import com.cooksys.twitterclone.entities.Tweet;

import java.util.List;

public interface TweetService {

    List<TweetResponseDto> getAllTweets();
}
