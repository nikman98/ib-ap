package com.stackroute.keepnote.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.keepnote.model.News;
import com.stackroute.keepnote.model.NewsUser;

/*
* This class is implementing the MongoRepository interface for Note.
* Annotate this class with @Repository annotation
* */

public interface NewsRepository extends MongoRepository<NewsUser, String> {

}
