package com.cooksys.twitterclone.controllers;

import java.util.List;

import com.cooksys.twitterclone.dtos.ContextDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.twitterclone.dtos.HashtagDto;
import com.cooksys.twitterclone.dtos.TweetResponseDto;
import com.cooksys.twitterclone.dtos.UserResponseDto;
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

	 //@PostMapping
//		public QuizResponseDto createQuiz(@RequestBody QuizRequestDto newQuiz) {
//			return quizService.createQuiz(newQuiz);
//		}
//	 @PostMapping
//	 public TweetResponseDto createTweet(@RequestBody TweetResponseDto newTweet) {
//		 return tweetService.createTweet(newTweet);
//	 }
//	 

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

}
