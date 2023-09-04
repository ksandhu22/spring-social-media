package com.cooksys.twitterclone.services.impl;


import java.util.List;
import java.util.Optional;

import com.cooksys.twitterclone.dtos.TweetResponseDto;
import com.cooksys.twitterclone.entities.Hashtag;
import com.cooksys.twitterclone.entities.Tweet;
import com.cooksys.twitterclone.exceptions.NotFoundException;
import com.cooksys.twitterclone.mappers.TweetMapper;
import com.cooksys.twitterclone.repositories.TweetRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import com.cooksys.twitterclone.dtos.HashtagDto;
import com.cooksys.twitterclone.mappers.HashtagMapper;
import com.cooksys.twitterclone.repositories.HashtagRepository;
import com.cooksys.twitterclone.services.HashtagService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HashtagServiceImpl implements HashtagService {
	
	private final HashtagRepository hashtagRepository;
    private final HashtagMapper hashtagMapper;
	private final TweetRepository tweetRepository;
	private final TweetMapper tweetMapper;

	@Override
	public List<HashtagDto> getAllHashtags() {
		return hashtagMapper.entitiesToDtos(hashtagRepository.findAll());
	}

	@Override
	public List<TweetResponseDto> getTweetsByLabel(String label) {

		Optional<Hashtag> foundTag = hashtagRepository.findById(label);

		return tweetMapper.entitiesToDtos(foundTag.get().getTweets());


//		Retrieves all (non-deleted) tweets tagged with the given hashtag label.
//		The tweets should appear in reverse-chronological order.
//		If no hashtag with the given label exists, an error should be sent in lieu of a response.
//		A tweet is considered "tagged" by a hashtag if the tweet has content and the hashtag's label appears in that content following a #


		// find all tweets
//		List<Tweet> tweets = tweetRepository.findByTweets(label);
//
//		return tweetMapper.entitiesToDtos(tweets);


		// Optional<Tweet> foundTweet = tweetRepository.;

//		if(foundTweet.isEmpty() || foundTweet.get().isDeleted()) {
//			throw new NotFoundException("Tweet not found");
//		}
//
//		return foundTweet;

//		Optional<Tweet> foundTweet = getOptionalTweetById(id);
//
//		return tweetMapper.entityToDto(foundTweet.get());
	}

}
