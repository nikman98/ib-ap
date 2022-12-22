package com.stackroute.keepnote.exception;

public class NewsAlreadyExistsException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public NewsAlreadyExistsException(String message)
	{
		super(message);
	}
}
