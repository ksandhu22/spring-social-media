package com.cooksys.twitterclone.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.twitterclone.dtos.TweetResponseDto;
import com.cooksys.twitterclone.dtos.UserResponseDto;
import com.cooksys.twitterclone.entities.Tweet;
import com.cooksys.twitterclone.exceptions.NotFoundException;
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

//        Deleted users should be excluded from the response.

        //Tweet foundTweet = tweetRepository.findById(id);
    	
//    	//List<Tweet> foundTweet = tweetRepository.findByLikedByUsers(id);
//
////        if(foundTweet.isEmpty() || foundTweet.get().isDeleted()) {
//            throw new NotFoundException("Tweet not found");
//        }

        // List<User> foundUsers = tweetRepository.findByLikedByUsersAndDeleted(foundTweet.get().getLikedByUsers(), false);
        // List<User> foundUsers = tweetRepository.findByLikedByUsersAndDeletedFalse(id);

        // does not filter out deleted users currently
        //return userMapper.entitiesToDtos(foundTweet.get().getLikedByUsers());
        // return userMapper.entitiesToDtos(foundUsers);
    	

//        return userMapper.entitiesToDtos(foundTweet.get().getLikedByUsers());
        // return userMapper.entitiesToDtos(foundUsers);

        //return userMapper.entitiesToDtos(foundTweet.get().getLikedByUsers().stream().filter(user -> !user.isDeleted()).toList());
    	
    	return null;
    }
    
    public List<TweetResponseDto> getTweetLikes(){
    	return null;
        return userMapper.entitiesToDtos(foundTweet.get().getLikedByUsers().stream().filter(user -> !user.isDeleted()).toList());
    }
}
