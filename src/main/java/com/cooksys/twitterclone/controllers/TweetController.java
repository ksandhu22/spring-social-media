package com.cooksys.twitterclone.controllers;

import java.util.List;

import com.cooksys.twitterclone.dtos.*;
import org.springframework.web.bind.annotation.*;

import com.cooksys.twitterclone.services.TweetService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tweets")
public class TweetController {
	
	 private final TweetService tweetService;

	 @GetMapping
	 public List<TweetResponseDto> getAllTweets(){
		 return tweetService.getAllTweets();
	 }

	 @PostMapping
	 public TweetResponseDto addTweet(@RequestBody TweetRequestDto newTweet) {
		 return tweetService.addTweet(newTweet);
	 }

	 @GetMapping("/{id}")
	 public TweetResponseDto getTweetById(@PathVariable Long id) {
		 return tweetService.getTweetById(id);
	 }

	 // getting all users that have liked a certain tweet
	 @GetMapping("/{id}/likes")
	 public List<UserResponseDto> getUsersByTweetId(@PathVariable Long id) {
		 return tweetService.getUsersByTweetId(id);
	 }
	 	 
	 // maybe change name to getTagsByTweetId
	 @GetMapping("/{id}/tags")
	 public List<HashtagDto> getTweetsByTag(@PathVariable Long id){
		 return tweetService.getHashtagByTweetId(id);
	 }

	 @GetMapping("/{id}/mentions")
	 public List<UserResponseDto> getUsersByMentions(@PathVariable Long id) {
		 return tweetService.getUsersByMentions(id);
	 }

	 @GetMapping("/{id}/reposts")
	 public List<TweetResponseDto> getRepostsByTweetId(@PathVariable Long id) {
		 return tweetService.getRepostsByTweetId(id);
	 }

	 @GetMapping("/{id}/replies")
	 public List<TweetResponseDto> getRepliesByTweetId(@PathVariable Long id) {
		 return tweetService.getRepliesByTweetId(id);
	 }
	 @GetMapping("/{id}/context")
	public ContextDto getContextFromTweetById(@PathVariable Long id) {
		 return tweetService.getContextFromTweetById(id);
	 }
	 @DeleteMapping("/{id}")
	 public TweetResponseDto deleteTweet(@PathVariable Long id, @RequestBody CredentialsDto user) {
		 return tweetService.deleteTweet(id, user);
	 }
	 @PostMapping("/{id}/like")
	 public void likeTweet(@PathVariable Long id, @RequestBody CredentialsDto user) {
			tweetService.likeTweet(id, user);
		}
}
