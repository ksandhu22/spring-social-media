package com.cooksys.twitterclone.services.impl;

import com.cooksys.twitterclone.dtos.TweetResponseDto;
import com.cooksys.twitterclone.mappers.TweetMapper;
import com.cooksys.twitterclone.repositories.TweetRepository;
import org.springframework.stereotype.Service;

import com.cooksys.twitterclone.services.TweetService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {

    private final TweetRepository tweetRepository;
    private final TweetMapper tweetMapper;

    @Override
    public List<TweetResponseDto> getAllTweets() {
        return tweetMapper.entitiesToDtos(tweetRepository.findAll().stream().filter(tweet -> !tweet.getDeleted()).toList());
    }
}
