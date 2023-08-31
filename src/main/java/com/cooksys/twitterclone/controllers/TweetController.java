package com.cooksys.twitterclone.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.twitterclone.dtos.TweetRequestDto;
import com.cooksys.twitterclone.dtos.TweetResponseDto;
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
	 @PostMapping
	 public TweetResponseDto createTweet(@RequestBody TweetResponseDto newTweet) {
		 return tweetService.createTweet(newTweet);
	 }
	 
	 
}
