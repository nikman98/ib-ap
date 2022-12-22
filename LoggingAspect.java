package com.stackroute.keepnote.aspectj;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAspect {
	
	Logger mylogger = LoggerFactory.getLogger(LoggingAspect.class);
	
	@Before("execution (* com.stackroute.keepnote.controller.NewsController.addNews(..))")
	public void beforeAddNews(JoinPoint jp) {
		mylogger.info("User is trying to add to Favourite.");
	}
	
	@After("execution (* com.stackroute.keepnote.controller.NewsController.addNews(..))")
	public void afterAddNews(JoinPoint jp) {
		mylogger.info("User has added to Favourite.");
	}
	
	@Before("execution (* com.stackroute.keepnote.controller.NewsController.deleteNews(..))")
	public void beforeDeleteNews(JoinPoint jp) {
		mylogger.info("User is trying to delete from Favourite.");
	}
	
	@After("execution (* com.stackroute.keepnote.controller.NewsController.deleteNews(..))")
	public void afterDeleteNews(JoinPoint jp) {
		mylogger.info("User has deleted from Favourite.");
	}
	
	@Before("execution (* com.stackroute.keepnote.controller.NewsController.getAllNewsByUserId(..))")
	public void beforeGetAllNewsByUserId(JoinPoint jp) {
		mylogger.info("User is trying to get all favourites.");
	}
	
	@After("execution (* com.stackroute.keepnote.controller.NewsController.getAllNewsByUserId(..))")
	public void afterGetAllNewsByUserId(JoinPoint jp) {
		mylogger.info("User got all favourites.");
	}
}
