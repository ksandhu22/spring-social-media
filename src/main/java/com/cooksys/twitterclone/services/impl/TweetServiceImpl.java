package com.cooksys.twitterclone.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cooksys.twitterclone.dtos.*;
import com.cooksys.twitterclone.entities.User;
import com.cooksys.twitterclone.exceptions.BadRequestException;
import com.cooksys.twitterclone.repositories.UserRepository;
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
//        public class TweetRequestDto {
//            private String content;
//            private CredentialsDto credentials;
//        }
//        public class CredentialsDto {
//            private String username;
//
//            private String password;
//        }

//        Creates a new simple tweet, with the author set to the user identified by the credentials in the request body.
//        If the given credentials do not match an active user in the database, an error should be sent in lieu of a response.
//        The response should contain the newly-created tweet.
//        Because this always creates a simple tweet, it must have a content property and may not have inReplyTo or repostOf properties.


//        IMPORTANT: when a tweet with content is created, the server must process the tweet's content for @{username} mentions and #{hashtag} tags.
//        There is no way to create hashtags or create mentions from the API, so this must be handled automatically!

        // find the author that made the tweet
        // get credentials/username from newTweet
        // find that whole user entity
        // add that user to the author of tweet

        User foundAuthor = userRepository.findByUsername(newTweet.getCredentials().getUsername());

        if(foundAuthor.getCredentials() == null) {
            throw new BadRequestException("user does not exist");
        }

        Tweet tweet = new Tweet();
        tweet.setAuthor(foundAuthor);
        return tweetMapper.entityToDto(tweet);

    //        public class Main {
    //            public static void main(String[] args) {
    //                Pattern pattern = Pattern.compile("w3schools", Pattern.CASE_INSENSITIVE);
    //                Matcher matcher = pattern.matcher("Visit W3Schools!");
    //                boolean matchFound = matcher.find();
    //                if(matchFound) {
    //                    System.out.println("Match found");
    //                } else {
    //                    System.out.println("Match not found");
    //                }
    //            }
    //        }
    //        Outputs Match found
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
}
