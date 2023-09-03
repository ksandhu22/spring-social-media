package com.cooksys.twitterclone.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.*;

import com.cooksys.twitterclone.dtos.*;
import com.cooksys.twitterclone.dtos.CredentialsDto;
import com.cooksys.twitterclone.entities.User;
import com.cooksys.twitterclone.exceptions.BadRequestException;
import com.cooksys.twitterclone.repositories.HashtagRepository;
import com.cooksys.twitterclone.repositories.UserRepository;
import com.cooksys.twitterclone.services.UserService;
import org.springframework.stereotype.Service;

import com.cooksys.twitterclone.entities.Hashtag;
import com.cooksys.twitterclone.entities.Tweet;
import com.cooksys.twitterclone.exceptions.NotFoundException;
import com.cooksys.twitterclone.mappers.HashtagMapper;
import com.cooksys.twitterclone.mappers.TweetMapper;
import com.cooksys.twitterclone.mappers.UserMapper;
import com.cooksys.twitterclone.repositories.TweetRepository;
import com.cooksys.twitterclone.services.TweetService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {

    private final TweetRepository tweetRepository;
    private final TweetMapper tweetMapper;
    private final UserMapper userMapper;
    private final HashtagMapper hashtagMapper;
    private final UserRepository userRepository;
    private final UserService userService;
    private final HashtagRepository hashtagRepository;

    public void parseForUserMentions(Tweet tweet) {
        Pattern pattern = Pattern.compile("@\\w+", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(tweet.getContent());

        List<User> mentionedUsers = new ArrayList<>();

        matcher.results().forEach(matchResult -> {
            String username = matchResult.group().substring(1);
            userRepository.findAll()
                    .stream()
                    .filter(user -> user.getCredentials().getUsername().equals(username))
                    .forEach(user -> {
                        mentionedUsers.add(user);
                        user.getMentionedTweets().add(tweet);
                    });
        });

        tweet.getMentionedUsers().addAll(mentionedUsers);
        tweetRepository.saveAndFlush(tweet);
        userRepository.saveAllAndFlush(mentionedUsers);

    }

    public void parseForHashtags(Tweet tweet) {
        Pattern pattern = Pattern.compile("@\\w+", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(tweet.getContent());

        List<Hashtag> hashtags = new ArrayList<>();

        matcher.results().forEach(matchResult -> {
            String hashtag = matchResult.group().substring(1);
            hashtagRepository.findAll()
                    .stream()
                    .filter(hashtag1 -> hashtag1.getLabel().equals(hashtag))
                    .forEach(hashtag1 -> {
                        hashtags.add(hashtag1);
                        hashtag1.getTweets().add(tweet);
                    });
        });

        tweet.getHashtags().addAll(hashtags);
        tweetRepository.saveAndFlush(tweet);
        hashtagRepository.saveAllAndFlush(hashtags);
    }

    public Optional<Tweet> getOptionalTweetById(Long id) throws NotFoundException{

        Optional<Tweet> foundTweet = tweetRepository.findById(id);

        if(foundTweet.isEmpty() || foundTweet.get().isDeleted()) {
            throw new NotFoundException("Tweet not found");
        }

        return foundTweet;
    }

    @Override
    public List<TweetResponseDto> getAllTweets() {
    	
        return tweetMapper.entitiesToDtos(tweetRepository.findAll().stream().filter(tweet -> !tweet.isDeleted()).toList());
    }


	@Override
	public TweetResponseDto addTweet(TweetRequestDto newTweet) {

        Optional<User> foundAuthor = Optional.ofNullable(userRepository.findByUsername(newTweet.getCredentials().getUsername()));

        // Optional<User> foundAuthor = Optional.ofNullable(foundAuthorEntity);

//        if(foundAuthor.getCredentials() == null) {
//            throw new BadRequestException("user does not exist");
//        }

        if(foundAuthor.isEmpty()) {
            throw new BadRequestException("user does not exist");
        }

        Tweet tweet = new Tweet();
        tweet.setAuthor(foundAuthor.get());
        parseForUserMentions(tweet);
        parseForHashtags(tweet);
        return tweetMapper.entityToDto(tweet);
	}

    @Override
    public TweetResponseDto getTweetById(Long id) throws NotFoundException{

        Optional<Tweet> foundTweet = getOptionalTweetById(id);

        return tweetMapper.entityToDto(foundTweet.get());
    }

    @Override
    public List<UserResponseDto> getUsersByTweetId(Long id) throws NotFoundException {

        Optional<Tweet> foundTweet = getOptionalTweetById(id);

        return userMapper.entitiesToDtos(foundTweet.get().getLikedByUsers().stream().filter(user -> !user.isDeleted()).toList());
    }

	@Override
	public List<HashtagDto> getHashtagByTweetId(Long id) throws NotFoundException {
        Optional<Tweet> foundTweet = getOptionalTweetById(id);

		List<Hashtag> tags = foundTweet.get().getHashtags();
		return hashtagMapper.entitiesToDtos(tags);

//        Retrieves the tags associated with the tweet with the given id.
//        If that tweet is deleted or otherwise doesn't exist, an error should be sent in lieu of a response.
//        IMPORTANT Remember that tags and mentions must be parsed by the server!
	}

    @Override
    public List<UserResponseDto> getUsersByMentions(Long id) throws NotFoundException {
        Optional<Tweet> foundTweet = getOptionalTweetById(id);

        return userMapper.entitiesToDtos(foundTweet.get().getMentionedUsers().stream().filter(user -> !user.isDeleted()).toList());
    }

    @Override
    public List<TweetResponseDto> getRepostsByTweetId(Long id) throws NotFoundException {
        Optional<Tweet> foundTweet = getOptionalTweetById(id);

        return tweetMapper.entitiesToDtos(foundTweet.get().getReposts().stream().filter(tweet -> !tweet.isDeleted()).toList());
    }

    @Override
    public List<TweetResponseDto> getRepliesByTweetId(Long id) throws NotFoundException {
        Optional<Tweet> foundTweet = getOptionalTweetById(id);

        return tweetMapper.entitiesToDtos(foundTweet.get().getReplies().stream().filter(tweet -> !tweet.isDeleted()).toList());
    }

    @Override
    public ContextDto getContextFromTweetById(Long id) throws NotFoundException {

        Optional<Tweet> foundTweet = getOptionalTweetById(id);

        ContextDto tweetContext = new ContextDto();
        tweetContext.setTarget(tweetMapper.entityToDto(foundTweet.get()));
        tweetContext.setBefore(getRepostsByTweetId(id));
        tweetContext.setAfter(getRepliesByTweetId(id));

        return tweetContext;
    }
    
    @Override
    public TweetResponseDto deleteTweet(Long id, CredentialsDto user) throws NotFoundException {
    	Optional<Tweet> foundTweet = getOptionalTweetById(id);
    	Optional<User> givenUser = userRepository.findByCredentialsUsername(user.getUsername());


    	if(foundTweet.isEmpty() || foundTweet.get().isDeleted() || givenUser.isEmpty() || givenUser.get().isDeleted() || givenUser.get().getCredentials().getPassword() == null) {
			throw new NotFoundException("Tweet not found");
		}
    	
    	foundTweet.get().setDeleted(true);
    	tweetRepository.saveAndFlush(foundTweet.get());
    	return tweetMapper.entityToDto(foundTweet.get());
    }
    @Override
	public void likeTweet(Long id, CredentialsDto user) {
    	Optional<Tweet> foundTweet = getOptionalTweetById(id);
    	Optional<User> givenUser = userRepository.findByCredentialsUsername(user.getUsername());
    	
    	if(foundTweet.isEmpty() || foundTweet.get().isDeleted() || givenUser.isEmpty() || givenUser.get().isDeleted() || givenUser.get().getCredentials().getPassword() == null) {
			throw new BadRequestException("nah");
		}
    	List<Tweet> tweet = new ArrayList<>();
    	tweet.add(foundTweet.get());
    	givenUser.get().setLikedTweets(tweet);
    	tweetRepository.saveAndFlush(foundTweet.get());
    }
}
