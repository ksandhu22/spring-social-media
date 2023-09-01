package com.cooksys.twitterclone.services;

import java.util.List;

import com.cooksys.twitterclone.dtos.*;
import com.cooksys.twitterclone.exceptions.NotFoundException;

public interface TweetService {

    List<TweetResponseDto> getAllTweets();

//	TweetResponseDto createTweet(TweetRequestDto newTweet);

    TweetResponseDto getTweetById(Long id) throws NotFoundException;

    List<UserResponseDto> getUsersByTweetId(Long id) throws NotFoundException;

	List<HashtagDto> getHashtagByTweetId(Long id) throws NotFoundException;

    List<UserResponseDto> getUsersByMentions(Long id) throws NotFoundException;

    List<TweetResponseDto> getRepostsByTweetId(Long id) throws NotFoundException;

    List<TweetResponseDto> getRepliesByTweetId(Long id) throws NotFoundException;

    ContextDto getContextFromTweetById(Long id) throws NotFoundException;

    TweetResponseDto addTweet(TweetRequestDto newTweet);
}
