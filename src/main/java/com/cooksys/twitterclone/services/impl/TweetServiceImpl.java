package com.cooksys.twitterclone.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.twitterclone.dtos.HashtagDto;
import com.cooksys.twitterclone.dtos.TweetResponseDto;
import com.cooksys.twitterclone.dtos.UserResponseDto;
import com.cooksys.twitterclone.entities.Hashtag;
import com.cooksys.twitterclone.entities.Tweet;
import com.cooksys.twitterclone.exceptions.NotFoundException;
import com.cooksys.twitterclone.mappers.HashtagMapper;
import com.cooksys.twitterclone.mappers.TweetMapper;
import com.cooksys.twitterclone.mappers.UserMapper;
import com.cooksys.twitterclone.repositories.TweetRepository;
import com.cooksys.twitterclone.services.TweetService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {

    private final TweetRepository tweetRepository;
    private final TweetMapper tweetMapper;
    private final UserMapper userMapper;
    private final HashtagMapper hashtagMapper;

    @Override
    public List<TweetResponseDto> getAllTweets() {
        return tweetMapper.entitiesToDtos(tweetRepository.findAll().stream().filter(tweet -> !tweet.isDeleted()).toList());
    }


//	@Override
//	public TweetResponseDto createTweet(TweetResponseDto newTweet) {
//		tweetRepository.save(tweetMapper.dtoToEntity(newTweet));
//        return newTweet;
//	}


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


    }
  

    
    

	@Override
	public List<HashtagDto> getHashtagByTweetId(Long id) throws NotFoundException {
		Optional<Tweet> foundTweet = tweetRepository.findById(id);

        if(foundTweet.isEmpty() || foundTweet.get().isDeleted()) {
            throw new NotFoundException("Tweet not found");
        }
		
		
		List<Hashtag> tags = foundTweet.get().getHashtags();
		return hashtagMapper.entitiesToDtos(tags);
	}
}
