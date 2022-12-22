package com.stackroute.keepnote.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.keepnote.model.News;
import com.stackroute.keepnote.service.NewsService;

/*
 * As in this assignment, we are working with creating RESTful web service, hence annotate
 * the class with @RestController annotation.A class annotated with @Controller annotation
 * has handler methods which returns a view. However, if we use @ResponseBody annotation along
 * with @Controller annotation, it will return the data directly in a serialized 
 * format. Starting from Spring 4 and above, we can use @RestController annotation which 
 * is equivalent to using @Controller and @ResposeBody annotation
 */
@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class NewsController {

	/*
	 * Autowiring should be implemented for the NoteService. (Use Constructor-based
	 * autowiring) Please note that we should not create any object using the new
	 * keyword
	 */
	 private NewsService newsService;


	@Autowired
	public NewsController(NewsService newsService) {
		this.newsService = newsService;
	}

	

	/*
	 * Define a handler method which will create a specific note by reading the
	 * Serialized object from request body and save the note details in the
	 * database.This handler method should return any one of the status messages
	 * basis on different situations: 
	 * 1. 201(CREATED) - If the note created successfully. 
	 * 2. 409(CONFLICT) - If the noteId conflicts with any existing user.
	 * 
	 * This handler method should map to the URL "/api/v1/note" using HTTP POST method
	 */

	 
	 @PostMapping("/news")
	    public ResponseEntity addNews(@RequestBody News news) {

	        ResponseEntity responseEntity = null;


	        if (newsService.createNews(news)) {
	            responseEntity = new ResponseEntity(news, HttpStatus.CREATED);
	        } else {
	            responseEntity = new ResponseEntity("Try again", HttpStatus.CONFLICT);
	        }


	        return responseEntity;
	    }
	/*
	 * Define a handler method which will delete a note from a database.
	 * This handler method should return any one of the status messages basis 
	 * on different situations: 
	 * 1. 200(OK) - If the note deleted successfully from database. 
	 * 2. 404(NOT FOUND) - If the note with specified noteId is not found.
	 *
	 * This handler method should map to the URL "/api/v1/note/{id}" using HTTP Delete
	 * method" where "id" should be replaced by a valid noteId without {}
	 */

	/*
	 * Define a handler method which will update a specific note by reading the
	 * Serialized object from request body and save the updated note details in a
	 * database. 
	 * This handler method should return any one of the status messages
	 * basis on different situations: 
	 * 1. 200(OK) - If the note updated successfully.
	 * 2. 404(NOT FOUND) - If the note with specified noteId is not found.
	 * 
	 * This handler method should map to the URL "/api/v1/note/{id}" using HTTP PUT method.
	 */
	
	/*
	 * Define a handler method which will get us the all notes by a userId.
	 * This handler method should return any one of the status messages basis on
	 * different situations: 
	 * 1. 200(OK) - If the note found successfully. 
	 * 
	 * This handler method should map to the URL "/api/v1/note" using HTTP GET method
	 */
	
	/*
	 * Define a handler method which will show details of a specific note created by specific 
	 * user. This handler method should return any one of the status messages basis on
	 * different situations: 
	 * 1. 200(OK) - If the note found successfully. 
	 * 2. 404(NOT FOUND) - If the note with specified noteId is not found.
	 * This handler method should map to the URL "/api/v1/note/{userId}/{noteId}" using HTTP GET method
	 * where "id" should be replaced by a valid reminderId without {}
	 * 
	 */
	 
//	 /{userid:.+}
	 
	 
	 
	 
//	 @GetMapping("/news/{userId}")
//	    public ResponseEntity<?> getAllNewsByUserId(@PathVariable("userId") String userId) 
//	 {
//		 List<News> userNews  = newsService.getAllNewsByUserId(userId);
//			if (userNews  != null) 
//			{
//				return new ResponseEntity <List<News>>(userNews , HttpStatus.OK);
//	 }
//			else 
//			{
//				return new ResponseEntity<String>("No news found", HttpStatus.CONFLICT);
//			}
//
//
//	    }
	 
	 
	 @GetMapping("/news/{userId}")
	    public ResponseEntity <?> getAllNewsByUserId(@PathVariable() String userId) {
	        ResponseEntity responseEntity = null;
	        List<News> userNews = newsService.getAllNewsByUserId(userId);

	        if (userNews != null) {
	            responseEntity = new ResponseEntity <List<News>> (userNews, HttpStatus.OK);
	        } else {
	            responseEntity = new ResponseEntity <String> ("You don't have any news articles added in your list", HttpStatus.OK);
	        }


	        return responseEntity;
	    }

	 
	 
	 
	 
	 @DeleteMapping("/news/{userId}/{id}")
		public ResponseEntity<?> deleteNews(@PathVariable("userId") String userId, @PathVariable("id") String id) 
	 {
			if (newsService.deleteNews(userId, id)) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("OK", "User Deleted");
				return new ResponseEntity<HashMap>(map, HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Unable to purge please try again", HttpStatus.NOT_FOUND);
			}
		}


	 
//	 @GetMapping("/")
//	 public ResponseEntity getAllNews()
}
