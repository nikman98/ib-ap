package com.stackroute.keepnote.service;

import com.stackroute.keepnote.exception.NewsNotFoundExeption;
import com.stackroute.keepnote.model.News;

import java.util.List;


public interface NewsService {
	
	/*
	 * Should not modify this interface. You have to implement these methods in
	 * corresponding Impl classes
	 */


    boolean createNews(News news);

//    boolean deleteNote(String userId, int noteId);
//
//    boolean deleteAllNotes(String userId) throws NewsNotFoundExeption;
//
//    News updateNote(News note, int id, String userId) throws NewsNotFoundExeption;
//
//    News getNoteByNoteId(String userId,int noteId) throws NewsNotFoundExeption;
    boolean deleteNews(String userId, String title);
    List<News> getAllNewsByUserId(String userId);


}
