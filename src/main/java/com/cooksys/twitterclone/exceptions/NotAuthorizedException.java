package com.cooksys.twitterclone.exceptions;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class NotAuthorizedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message;
}
