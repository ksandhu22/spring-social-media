package com.cooksys.twitterclone.controllers.advice;

//import javax.servlet.http.HttpServletRequest;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.cooksys.twitterclone.dtos.ErrorDto;
import com.cooksys.twitterclone.exceptions.BadRequestException;

//import javassist.NotFoundException;
public class TwitterControllerAdvice {
//
//
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.client.HttpClientErrorException.BadRequest;
//
//import com.cooksys.twitterclone.dtos.ErrorDto;
//import com.cooksys.twitterclone.exceptions.BadRequestException;
//
//import javassist.NotFoundException;
public class TwitterControllerAdvice {

//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	@ExceptionHandler(BadRequest.class)
//	public ErrorDto handleBadRequestException(HttpServletRequest request, BadRequestException badRequestException) {
//		return new ErrorDto(badRequestException.getMessage());
//	}
//
//	@ResponseStatus(HttpStatus.NOT_FOUND)
//	@ExceptionHandler(NotFoundException.class)
//	public ErrorDto handleNotFoundException(HttpServletRequest request, NotFoundException notFoundException) {
//		return new ErrorDto(notFoundException.getMessage());
//	}
}
